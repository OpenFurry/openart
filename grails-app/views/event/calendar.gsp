<html>
    <head>
        <title><g:message code="openart.event.views.calendar" default="Calendar" /></title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="breadcrumbs">
            <g:link controller="group"><g:message code="openart.group.plural" default="Groups" /></g:link> &raquo;
            <g:link controller="group" action="show" id="${group.slug}">${group.title.encodeAsHTML()}</g:link> &raquo;
            <g:message code="openart.event.plural" default="Events" />
        </div>
        <div style="text-align: center">
            <g:link controller="event" action="calendar" id="${group.slug}" params="[month: prevMonth.month + 1, year: prevMonth.year]">
                &laquo; <g:message code="openart.event.views.calendar.prevMonth" default="Previous month" />
            </g:link>
            | <strong><g:formatDate date="${monthStart.time}" format="MMMMMMMMM yyyy" /></strong> |
            <g:link controller="event" action="calendar" id="${group.slug}" params="[month: nextMonth.month + 1, year: nextMonth.year]">
                <g:message code="openart.event.views.calendar.nextMonth" default="Next month" /> &raquo;
            </g:link>
        </div>
        <table class="calendar">
            <thead>
                <tr>
                    <th><g:message code="openart.event.days.sunday" default="Sunday" /></th>
                    <th><g:message code="openart.event.days.monday" default="Monday" /></th>
                    <th><g:message code="openart.event.days.tuesday" default="Tuesday" /></th>
                    <th><g:message code="openart.event.days.wednesday" default="Wednesday" /></th>
                    <th><g:message code="openart.event.days.thursday" default="Thursday" /></th>
                    <th><g:message code="openart.event.days.friday" default="Friday" /></th>
                    <th><g:message code="openart.event.days.saturday" default="Saturday" /></th>
                </tr>
            </thead>
            <tbody>
                <of:calendarAsTDs start="${monthStart}" end="${monthEnd}" events="${events}" />
            </tbody>
            <tfoot>
                <tr>
                    <th><g:message code="openart.event.days.sunday" default="Sunday" /></th>
                    <th><g:message code="openart.event.days.monday" default="Monday" /></th>
                    <th><g:message code="openart.event.days.tuesday" default="Tuesday" /></th>
                    <th><g:message code="openart.event.days.wednesday" default="Wednesday" /></th>
                    <th><g:message code="openart.event.days.thursday" default="Thursday" /></th>
                    <th><g:message code="openart.event.days.friday" default="Friday" /></th>
                    <th><g:message code="openart.event.days.saturday" default="Saturday" /></th>
                </tr>
            </tfoot>
        </table>
        <div style="text-align: center">
            <g:link controller="event" action="calendar" id="${group.slug}" params="[month: prevMonth.month + 1, year: prevMonth.year]">
                &laquo; <g:message code="openart.event.views.calendar.prevMonth" default="Previous month" />
            </g:link>
            | <strong><g:formatDate date="${monthStart.time}" format="MMMMMMMMM yyyy" /></strong> |
            <g:link controller="event" action="calendar" id="${group.slug}" params="[month: nextMonth.month + 1, year: nextMonth.year]">
                <g:message code="openart.event.views.calendar.nextMonth" default="Next month" /> &raquo;
            </g:link>
        </div>
    </body>
</body>
