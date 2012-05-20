package us.jnsq.openfurry

class AdminController {
    static defaultAction = "index"

    def authenticateService

    def warningService

    def permissionsService

    def messagingService

    def marketService

    def index = {
        if (!authenticateService.ifAnyGranted("ROLE_STAFF.ROLE_ADMIN")) {
            redirect(action: "list")
            return
        }

        // otherwise, admin dashboard
    }

    def list = {
        def adminRole = Role.findByAuthority("ROLE_ADMIN")
        def staffRole = Role.findByAuthority("ROLE_STAFF")
        def governorRole = Role.findByAuthority("ROLE_GOVERNOR")

        [admins: adminRole, staff: staffRole, governors: governorRole]
    }

    def troubleTicket = {
        if (request.getMethod() == "GET") {
            return
        }

        def ticket = new Issue()
        ticket.properties = params
        ticket.submitter = authenticateService.principal().domainClass
        ticket.votes = 0

        if (ticket.save(flush: true)) {
            def user = User.get(authenticateService.principal().domainClass.id)
            messagingService.transientMessage(
                user,
                grailsApplication.config.openfurry.user.messageTypes.success,
                'openfurry.messages.ticket.new',
                "Ticket submitted"
            )
            messagingService.persistentMessageRole(
                "ROLE_STAFF",
                grailsApplication.config.openfurry.user.messageTypes.warning,
                'openfurry.messages.ticket.new',
                "New trouble ticket: {0}",
                ticket.class.toString().split("\\.")[-1],
                ticket.id
            )

            marketService.transact(user, "TroubleTicket.create(memberClass:${user.memberClass})")

            redirect(action: "ticket", id: ticket.id)
        } else {
            [instance: ticket]
        }
    }

    def tickets = {
        def list = Issue.withCriteria {
            and {
                if (params.type) {
                    eq("type", Integer.parseInt(params.type))
                } else {
                    between("type", 3, 6)
                }
                if (params.status) {
                    eq("status", Integer.parseInt(params.status))
                }
            }
        }

        [issueList: list]
    }

    def ticket = {
        def ticket = Issue.get(params.id)

        if (!ticket) {
            response.sendError(404)
            return
        }

        if (!permissionsService.troubleTickets.userCanView(ticket)) {
            response.sendError(403)
            return
        }

        [issue: ticket]
    }

    def updateTicket = {
        def ticket = Issue.get(params.id)

        if (!ticket) {
            response.sendError(404)
            return
        }

        if (request.getMethod() == "GET") {
            render(view: "troubleTicket", model: [instance: ticket])
        } else {
            ticket.properties = params
            if (ticket.save(flush: true)) {
                redirect(action: "ticket", id: ticket.id)
            } else {
                render(view: "troubleTicket", model: [instance: ticket])
            }
        }
    }

    def warn = {
        def user = User.findByUsername(params.id)

        if (!user) {
            response.sendError(404)
            return
        }

        if (params.warningLevel && params.reasonCode) {
            warningService.warn(
                user,
                grailsApplication.config.openfurry.user.warning."${params.warningLevel}",
                params.reasonCode)
            messagingService.transientMessage(
                user,
                grailsApplication.config.openfurry.user.messageTypes.success,
                'openfurry.messages.warning.applied',
                "Warning applied"
            )


            redirect(controller: "user", action: "show", params: [username: user.username])
        } else {
            [user: user]
        }
    }

    def praise = {
        def user = User.findByUsername(params.id)

        if (!user) {
            response.sendError(404)
            return
        }

        if (params.praiseLevel && params.reasonCode) {
            warningService.praise(
                user,
                grailsApplication.config.openfurry.user.warning."${params.praiseLevel}",
                params.reasonCode)
            messagingService.transientMessage(
                user,
                grailsApplication.config.openfurry.user.messageTypes.success,
                'openfurry.messages.warning.applied',
                "Warning applied"
            )

            redirect(controller: "user", action: "show", params: [username: user.username])
        } else {
            [user: user]
        }
    }

    def ban = {
        def user = User.findByUsername(params.id)

        warningService.ban(user)
        messagingService.transientMessage(
            user,
            grailsApplication.config.openfurry.user.messageTypes.success,
            'openfurry.message.ban.applied',
            "Ban applied"
        )

        redirect(controller: "user", action: "show", params: [username: user.username])
    }

    def unban = {
        def user = User.findByUsername(params.id)

        warningService.unban(user)
        messagingService.transientMessage(
            user,
            grailsApplication.config.openfurry.user.messageTypes.success,
            'openfurry.messages.ban.lifted',
            "Ban lifted"
        )

        redirect(controller: "user", action: "show", params: [username: user.username])
    }

    def takeDown = {
        def uo = UserObject.get(params.id)
        if (!uo) {
            response.sendError(404)
            return
        }
        
        uo.takenDown = true
        uo.save()

        messagingService.transientMessage(
            User.get(authenticateService.principal().domainClass.id),
            grailsApplication.config.openfurry.user.messageTypes.success,
            "openfurry.messages.uo.takedown.applied",
            "Submission taken down"
        )
        messagingService.persistentMessage(
            uo.owner,
            grailsApplication.config.openfurry.user.messageTypes.failure,
            "openfurry.messages.uo.takenDown",
            "Your submission, {0}, was taken down by a staff member; see the submission for details.",
            uo.class.toString().split("\\.")[-1],
            uo.id
        )
        
        redirect(controller: "view", action: "show", id: uo.id)
    }

    def unTakeDown = {
        def uo = UserObject.get(params.id)
        if (!uo) {
            response.sendError(404)
            return
        }

        uo.takenDown = false
        uo.save(flush: true)

        messagingService.transientMessage(
            User.get(authenticateService.principal().domainClass.id),
            grailsApplication.config.openfurry.user.messageTypes.success,
            "openfurry.messages.uo.takedown.lifted",
            "Submission put back up"
        )
        messagingService.persistentMessage(
            uo.owner,
            grailsApplication.config.openfurry.user.messageTypes.success,
            "openfurry.messages.uo.unTakenDown",
            "Your submission, {0}, was put back up by a staff member; see the submission for details.",
            uo.class.toString().split("\\.")[-1],
            uo.id
        )

        redirect(controller: "view", action: "show", id: uo.id)
    }
}
