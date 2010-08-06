package com.mjs_svc.openfurry

class AdminController {
    static defaultAction = "index"

    def authenticateService

    def warningService

    def permissionsService

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
            // TODO message user, staff
            // TODO transaction

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
            // TODO message admin

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
            // TODO message admin

            redirect(controller: "user", action: "show", params: [username: user.username])
        } else {
            [user: user]
        }
    }

    def ban = {
        def user = User.findByUsername(params.id)

        warningService.ban(user)

        redirect(controller: "user", action: "show", params: [username: user.username])
    }

    def unban = {
        def user = User.findByUsername(params.id)

        warningService.unban(user)

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

        // TODO message user, message admin
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

        // TODO message user, message admin
        redirect(controller: "view", action: "show", id: uo.id)
    }
}
