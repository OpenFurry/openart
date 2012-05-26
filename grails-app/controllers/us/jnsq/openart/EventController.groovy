package us.jnsq.openart

class EventController {
    def authenticateService
    def permissionsService

    def calendar = {
        def group = UserGroup.findBySlug(params.id)
        
        if (!group) {
            response.sendError(404) // TODO i18n
            return
        }

        if (!permissionsService.groups.userCanRead(group)) {
            response.sendError(403)
            return
        }

        def month = params.month ? Integer.parseInt(params.month) - 1 : new GregorianCalendar().get(Calendar.MONTH)
        def year = params.year ? Integer.parseInt(params.year) : new GregorianCalendar().get(Calendar.YEAR)

        // Get the start of the month given the year and month data
        def monthStart = new GregorianCalendar(year, month, 1)

        // Get the end of the month by sacrificing a chicken over monthStart with an obsidian knife
        // (really, the groovy dates roll around nicely - December + 1 is January, 1 - 1 = 31)
        def monthEnd = new GregorianCalendar(year, month, 1)
        monthEnd.set(Calendar.MONTH, monthEnd.get(Calendar.MONTH) + 1)
        monthEnd.set(Calendar.DATE, monthEnd.get(Calendar.DATE) - 1)
        monthEnd.time // This makes sure everything rolls over

        def events = Event.withCriteria {
            and {
                eq("group", group)
                or {
                    between("eventDateStart", monthStart.time, monthEnd.time)
                    between("eventDateEnd", monthStart.time, monthEnd.time)
                }
            }
            // order("eventDateStart", "desc")
        }

        def prevMonth = [
            month: monthStart.get(Calendar.MONTH) == 0 ? 11 : monthStart.get(Calendar.MONTH) - 1,
            year: monthStart.get(Calendar.MONTH) == 0 ? monthStart.get(Calendar.YEAR) - 1 : monthStart.get(Calendar.YEAR)
        ]

        def nextMonth = [
            month: (monthStart.get(Calendar.MONTH) + 1) % 12,
            year: monthStart.get(Calendar.MONTH) == 11 ? monthStart.get(Calendar.YEAR) + 1 : monthStart.get(Calendar.YEAR)
        ]

        [prevMonth: prevMonth, nextMonth: nextMonth, monthStart: monthStart, monthEnd: monthEnd, events: events, group: group]
    }

    def create = {
        if (params.eventId) {
            params.eventId = null
        }
        
        def group = UserGroup.findBySlug(params.id)
        if (!group) {
            response.sendError(404) // TODO i18n
            return
        }

        if (!permissionsService.groups.userCanPost(group)) {
            response.sendError(403)
            return
        }

        [group: group.id]
    }

    def edit = {
        def event = Event.get(params.eventId)
        
        if (!event) {
            response.sendError(403) // TODO i18n
            return
        }
        
        if (!permissionsService.groups.userCanPost(event.group)) {
            response.sendError(403) // TODO i18n
            return
        }

        render(view: 'create', model: [instance: event, group: event.group.id])
    }

    def save = {
        def event
        if (params.eventId) {
            event = Event.get(params.eventId)
        } else {
            event = new Event()
        }

        def group = UserGroup.get(params.groupId)
        if (!group) {
            response.sendError(404) // TODO i18n
            return
        }

        if (!permissionsService.groups.userCanPost(group)) {
            response.sendError(403)
            return
        }

        event.properties = params
        event.owner = authenticateService.principal().domainClass
        event.group = group

        if (event.save(flush: true)) {
            redirect(action: 'show', id: event.id)
        } else {
            render(view: 'create', model: [instance: event, group: group.id])
        }
    }

    def show = {
        def event = Event.get(params.id)

        if (!event) {
            response.sendError(404) // TODO i18n
            return
        }

        if (!permissionsService.groups.userCanRead(event.group)) {
            response.sendError(403) // TODO i18n
            return
        }

        [event: event]
    }

    def delete = {}
}
