<html><!-- TODO i18n -->
    <head>
        <title>CREATE EVENT</title>
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
                        <th colspan="2">EVENT DETAILS</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name">TITLE</th>
                        <td class="value"><g:textField name="title" value="${instance?.title}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name">DESCRIPTION</th>
                        <td class="value"><g:textArea name="description" rows="10" cols="50" value="${instance?.description}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name">START DATE/TIME</th>
                        <td class="value"><g:datePicker name="eventDateStart" value="${instance?.eventDateStart}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name">END DATE/TIME</th>
                        <td class="value"><g:datePicker name="eventDateEnd" value="${instance?.eventDateEnd}" /></td>
                    </tr>
                </tbody>
            </table>
            <div class="buttons"><input type="submit" /></div>
        </g:form>
    </body>
</html>
