<html>
    <head>
        <title><g:message code="openfurry.event" default="Event" /> - ${event.title}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div id="breadcrumbs">
            <g:link controller="group"><g:message code="openfurry.display.navigation.groups" default="Groups and events" /></g:link> &raquo;
            <g:link controller="group" action="show" id="${event.group.slug}">${event.group.title}</g:link> &raquo;
            <g:link controller="event" action="calendar" id="${event.group.slug}"><g:message code="openfurry.event.plural" default="Events" /></g:link> &raquo;
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
                            <th colspan="2">TIMES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="prop">
                            <th class="name">START</th>
                            <td class="value"><g:formatDate date="${event.eventDateStart}" /></td>
                        </tr>
                        <tr class="prop">
                            <th class="name">END</th>
                            <td class="value"><g:formatDate date="${event.eventDateEnd}" /></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="eventLocation block">
                <div class="shadow">LOCATION</div>
                <a href="http://maps.google.com/maps?q=${event.location.encodeAsURL()}" target="_blank"><img src="http://maps.google.com/maps/api/staticmap?markers=color:blue|${event.location.encodeAsURL()}&size=400x400&sensor=false" /></a>
                <p>${event.location.encodeAsHTML()}</p>
            </div>
            <br clear="all" />
        </div>
    </body>
</html>
