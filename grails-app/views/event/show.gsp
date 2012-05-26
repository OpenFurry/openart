<html>
    <head>
        <title><g:message code="openart.event" default="Event" /> - ${event.title}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div id="breadcrumbs">
            <g:link controller="group"><g:message code="openart.display.navigation.groups" default="Groups and events" /></g:link> &raquo;
            <g:link controller="group" action="show" id="${event.group.slug}">${event.group.title}</g:link> &raquo;
            <g:link controller="event" action="calendar" id="${event.group.slug}"><g:message code="openart.event.plural" default="Events" /></g:link> &raquo;
            ${event.title}
        </div>
        <div class="eventDescription block">
            <p><of:linking>~${event.owner.username}</of:linking></p>
            <of:linking><markdown:renderHtml>${event.description.encodeAsHTML()}</markdown:renderHtml></of:linking>
            <hr />
            <div class="eventTimes">
                <table>
                    <thead>
                        <tr>
                            <th colspan="2"><g:message code="openart.event.date.plural" default="Dates/Times" /></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="prop">
                            <th class="name"><g:message code="openart.event.date.startDate" default="Start date/time" /></th>
                            <td class="value"><g:formatDate date="${event.eventDateStart}" /></td>
                        </tr>
                        <tr class="prop">
                            <th class="name"><g:message code="openart.event.date.endDate" default="End date/time" /></th>
                            <td class="value"><g:formatDate date="${event.eventDateEnd}" /></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <g:if test="${event.location}">
                <div class="eventLocation block">
                    <div class="shadow"><g:message code="openart.event.location" default="Location" /></div>
                    <a href="http://maps.google.com/maps?q=${event.location.encodeAsURL()}" target="_blank"><img src="http://maps.google.com/maps/api/staticmap?markers=color:blue|${event.location.encodeAsURL()}&size=400x400&sensor=false" /></a>
                    <p>${event.location.encodeAsHTML()}</p>
                </div>
            </g:if>
            <br clear="all" />
        </div>
    </body>
</html>
