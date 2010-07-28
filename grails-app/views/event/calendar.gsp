<html>
    <head>
        <title><g:message code="openfurry.event.views.calendar" default="Calendar" /></title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <table class="calendar">
            <thead>
                <tr>
                    <th><g:message code="openfurry.event.days.sunday" default="Sunday" /></th>
                    <th><g:message code="openfurry.event.days.monday" default="Monday" /></th>
                    <th><g:message code="openfurry.event.days.tuesday" default="Tuesday" /></th>
                    <th><g:message code="openfurry.event.days.wednesday" default="Wednesday" /></th>
                    <th><g:message code="openfurry.event.days.thursday" default="Thursday" /></th>
                    <th><g:message code="openfurry.event.days.friday" default="Friday" /></th>
                    <th><g:message code="openfurry.event.days.saturday" default="Saturday" /></th>
                </tr>
            </thead>
            <tbody>
                <of:calendarAsTDs start="${monthStart}" end="${monthEnd}" events="${events}" />
            </tbody>
            <tfoot>
                <tr>
                    <th><g:message code="openfurry.event.days.sunday" default="Sunday" /></th>
                    <th><g:message code="openfurry.event.days.monday" default="Monday" /></th>
                    <th><g:message code="openfurry.event.days.tuesday" default="Tuesday" /></th>
                    <th><g:message code="openfurry.event.days.wednesday" default="Wednesday" /></th>
                    <th><g:message code="openfurry.event.days.thursday" default="Thursday" /></th>
                    <th><g:message code="openfurry.event.days.friday" default="Friday" /></th>
                    <th><g:message code="openfurry.event.days.saturday" default="Saturday" /></th>
                </tr>
            </tfoot>
        </table>
    </body>
</body>
