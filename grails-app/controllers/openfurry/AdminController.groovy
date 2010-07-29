package openfurry

class AdminController {
    static defaultAction = "index"

    def authenticateService

    def warningService

    def index = {
        if (!authenticateService.ifAnyGranted("ROLE_STAFF.ROLE_ADMIN")) {
            redirect(action: "list")
            return
        }
    }

    def list = {
        def adminRole = Role.findByAuthority("ROLE_ADMIN")
        def staffRole = Role.findByAuthority("ROLE_STAFF")
        def governorRole = Role.findByAuthority("ROLE_GOVERNOR")

        [admins: adminRole, staff: staffRole, governors: governorRole]
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

        [tickets: list]
    }

    def ticket = {
        def ticket = Issue.get(params.id)

        if (!ticket) {
            response.sendError(404)
            return
        }

        [ticket: ticket]
    }

    def updateTicket = {
        def ticket = Issue.get(params.id)

        if (!ticket) {
            response.sendError(404)
            return
        }

        if (request.getMethod() == "GET") {
            [ticket: ticket]
        } else {
            ticket.properties = params
            if (ticket.save(flush: true)) {
                redirect(action: "ticket", id: ticket.id)
            } else {
                [ticket: ticket]
            }
        }
    }

    def warn = {
        def user = Person.findByUsername(params.id)

        if (!user) {
            response.sendError(404)
            return
        }

        if (params.warningLevel && params.reasonCode) {
            warningService.warn(
                user,
                grailsApplication.config.openfurry.user.warning."%{params.warningLevel}",
                params.reasonCode)
            // TODO message admin

            redirect(controller: "person", action: "show", id: user.username)
        } else {
            [user: user]
        }
    }

    def praise = {
        def user = Person.findByUsername(params.id)

        if (!user) {
            response.sendError(404)
            return
        }

        if (params.praiseLevel && params.reasonCode) {
            warningService.praise(
                user,
                grailsApplication.config.openfurry.user.warning."%{params.praiseLevel}",
                params.reasonCode)
            // TODO message admin

            redirect(controller: "person", action: "show", id: user.username)
        } else {
            [user: user]
        }
    }

    def ban = {
        def user = Person.findByUsername(params.id)

        warningService.ban(user)

        redirect(controller: "person", action: "show", id: user.username)
    }

    def unban = {
        def user = Person.findByUsername(params.id)

        warningService.unban(user)

        redirect(controller: "person", action: "show", id: user.username)
    }
}
