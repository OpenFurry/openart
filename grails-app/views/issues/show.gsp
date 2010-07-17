<html>
    <head>
        <title><!-- TODO --></title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <th><g:message code="openfurry.issue.id" default="ID" /></th>
                    <th><g:message code="openfurry.issue.title" default="Issue" /></th>
                    <th><g:message code="openfurry.issue.submitter" default="Submitted by" /></th>
                    <th><g:message code="openfurry.issue.type" default="Issue type" /></th>
                    <th><g:message code="openfurry.issue.status" default="Issue status" /></th>
                    <th><g:message code="openfurry.issue.JIRAIssue" default="JIRA Issue" /></th>
                    <th><g:message code="openfurry.issue.votes" default="Votes" /></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${issue.id}</td>
                    <td>${issue.title}</td>
                    <td>%{issue.submitter.username}</td>
                    <td><g:message code="openfurry.issues.type.${grailsApplication.config.openfurry.issue.type.repr[it.type]}" default="${grailsApplication.config.openfurry.issue.type.repr[it.type]}" /></td>
                    <td><g:message code="openfurry.issues.status.${grailsApplication.config.openfurry.issue.status.repr[it.status]}" default="${grailsApplication.config.openfurry.issue.status.repr[it.status]}" /></td>
                    <td>${issue.votes}</td>
                <tr>
                    <td colspan="5" class="block">%{issue.description}</td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
