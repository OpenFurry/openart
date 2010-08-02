<html>
    <head>
        <title><g:message code="openfurry.ticket" default="Trouble Ticket" /> ${issue.id}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <th style="width: 5%"><g:message code="openfurry.issue.id" default="ID" /></th>
                    <th style="width: 40%"><g:message code="openfurry.issue.title" default="Issue" /></th>
                    <th style="width: 15%"><g:message code="openfurry.issue.submitter" default="Submitted by" /></th>
                    <th style="width: 15%"><g:message code="openfurry.issue.type" default="Issue type" /></th>
                    <th style="width: 10%"><g:message code="openfurry.issue.status" default="Issue status" /></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        <g:if test="${issue.status == 3 || issue.status == 4}"><s>${issue.id}</s></g:if>
                        <g:else>${issue.id}</g:else>
                    </td>
                    <td>${issue.title.encodeAsHTML()}</td>
                    <td><of:linking noImages="true">${issue.submitter.encodeAsHTML()}</of:linking></td>
                    <td class="${grailsApplication.config.openfurry.issue.type.repr[issue.type]}"><g:message code="openfurry.issue.type.${grailsApplication.config.openfurry.issue.type.repr[issue.type]}" default="${grailsApplication.config.openfurry.issue.type.repr[issue.type]}" /></td>
                    <td><g:message code="openfurry.issue.status.${grailsApplication.config.openfurry.issue.status.repr[issue.status]}" default="${grailsApplication.config.openfurry.issue.status.repr[issue.status]}" /></td>
                </tr>
                <tr>
                    <td colspan="7" class="block" style="padding: 1em;">
                        <h3><g:message code="openfurry.issue.description" default="Description" /></h3>
                        <hr />
                        <of:linking><markdown:renderHtml>${issue.description.encodeAsHTML()}</markdown:renderHtml></of:linking>
                    </td>
                </tr>
            </tbody>
        </table>
        <g:render template="/comments" model="[object: issue]" />
    </body>
</html>
