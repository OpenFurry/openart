<html>
    <head>
        <title><g:message code="openfurry.event.views.create" default="Create event" /></title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:hasErrors bean="${instance}">
        <div class="errors">
            <g:renderErrors bean="${instance}" as="list" />
        </div>
        </g:hasErrors>
        <g:form action="save" method="post">
            <input type="hidden" name="groupId" value="${group}" />
            <input type="hidden" name="eventId" value="${instance?.id}" />
            <table>
                <thead>
                    <tr>
                        <th colspan="2"><g:message code="openfurry.event.details" default="Event details" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.event.title" default="Title" /></th>
                        <td class="value"><g:textField name="title" value="${instance?.title}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.event.description" default="Description" />
                            <div class="message">
                                <p><g:link controller="flatpage" action="show" id="markdown"><g:message code="openfurry.messages.markdownOkay" default="Markdown allowed" /></g:link></p>
                                <p><g:link controller="flatpage" action="show" id="linking"><g:message code="openfurry.messages.linkingOkay" default="Linking allowed" /></g:link></p>
                            </div>
                        </th>
                        <td class="value"><g:textArea name="description" rows="10" cols="50" value="${instance?.description}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.event.date.startDate" default="Start date/time" /></th>
                        <td class="value"><g:datePicker name="eventDateStart" value="${instance?.eventDateStart}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.event.date.endDate" default="End date/time" /></th>
                        <td class="value"><g:datePicker name="eventDateEnd" value="${instance?.eventDateEnd}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.event.location" default="Location" /></th>
                        <td class="value"><g:textField name="location" value="${instance?.location}" /></td>
                    </tr>
                </tbody>
            </table>
            <div class="buttons"><input type="submit" /></div>
        </g:form>
    </body>
</html>
