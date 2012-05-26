package us.jnsq.openart

class CalendarTagLib {
    static namespace = "of"
    
    def calendarAsTDs = { attrs ->
        def start = attrs['start']
        def curr = start
        def end = attrs['end']
        def events = attrs['events']

        // Empty first cells
        out << "<tr>\n"
        def dayOfWeek = Calendar.SUNDAY
        if (start.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            for (i in (Calendar.SUNDAY..(start.get(Calendar.DAY_OF_WEEK) - 1))) {
                out << '<td class="empty"></td>\n'
                dayOfWeek++
            }
        }

        // Loop through each date, print out any events
        for (i in (1..end.get(Calendar.DATE))) {
            out << "<td><strong>$i</strong>\n"
            events.each { event ->
                if (event.eventDateStart >= curr.time 
                    && event.eventDateStart <= curr.time.next()
                    && event.eventDateEnd >= curr.time
                    && event.eventDateEnd <= curr.time.next()) {
                    out << """
                        <a class="event eventStart eventEnd" href="${createLink(action: 'show', id: event.id)}">${event.title}</a>
                    """
                    return
                }
                if (event.eventDateStart >= curr.time && event.eventDateStart <= curr.time.next()) {
                    out << """
                        <a class="event eventStart" href="${createLink(action: 'show', id: event.id)}">${event.title}</a>
                    """
                    return
                }
                if (event.eventDateEnd >= curr.time && event.eventDateEnd <= curr.time.next()) {
                    out << """
                        <a class="event eventEnd" href="${createLink(action: 'show', id: event.id)}">${event.title}</a>
                    """
                    return
                }
                if (event.eventDateStart < curr.time && event.eventDateEnd > curr.time) {
                    out << """
                        <a class="event" href="${createLink(action: 'show', id: event.id)}">${event.title} ${message(code: "openart.event.continued", default: "cont'd")}</a>
                    """
                    return
                }
            }
            out << "</td>\n"

            if (dayOfWeek == Calendar.SATURDAY) {
                out << "</tr><tr>\n"
                dayOfWeek = Calendar.SUNDAY
            } else {
                dayOfWeek++
            }
            curr.set(Calendar.DATE, curr.get(Calendar.DATE) + 1)
        }

        if (dayOfWeek != Calendar.SUNDAY) {
            for (i in (dayOfWeek..Calendar.SATURDAY)) {
                out << '<td class="empty"></td>\n'
            }
        }
        out << "</tr>\n"
    }

}
