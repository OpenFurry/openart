<html>
    <head>
        <title><g:message code="openart.ticket.plural" default="Trouble Tickets" /></title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="verticalTabs">
            <ul class="nav">
                <li class="block ${params.type ? '' : 'selected'}"><a href="${params.status ? '?status=' + params.status : ''}"><g:message code="openart.issue.types.all" default="All" /></a></li>
                <li class="block ${params.type == '3' ? 'selected' : ''}"><a href="?type=0${params.status ? '&status=' + params.status : ''}"><g:message code="openart.issue.types.ToSViolation" default="ToS Violation" /></a></li>
                <li class="block ${params.type == '4' ? 'selected' : ''}"><a href="?type=1${params.status ? '&status=' + params.status : ''}"><g:message code="openart.issue.types.AUP Violation" default="AUP Violation" /></a></li>
                <li class="block ${params.type == '5' ? 'selected' : ''}"><a href="?type=2${params.status ? '&status=' + params.status : ''}"><g:message code="openart.issue.types.AccountIssue" default="Account Issue" /></a></li>
                <li class="block ${params.type == '6' ? 'selected' : ''}"><a href="?type=2${params.status ? '&status=' + params.status : ''}"><g:message code="openart.issue.types.Harrassment" default="Harrassment" /></a></li>
            </ul>
            <div class="content block" style="min-height: 20em;">
                <div class="horizontalTabs">
                    <ul class="nav">
                        <li class="block ${params.status ? '' : 'selected'}"><a href="${params.type ? '?type=' + params.type : ''}"><g:message code="openart.issue.status.all" default="All" /></a></li>
                        <li class="block ${params.status == '0' ? 'selected' : ''}"><a href="?status=${0}${params.type ? '&type=' + params.type : ''}"><g:message code="openart.issue.status.Suggestion" default="Suggestion" /></a></li>
                        <li class="block ${params.status == '2' ? 'selected' : ''}"><a href="?status=${2}${params.type ? '&type=' + params.type : ''}"><g:message code="openart.issue.status.Accepted" default="Accepted" /></a></li>
                        <li class="block ${params.status == '3' ? 'selected' : ''}"><a href="?status=${3}${params.type ? '&type=' + params.type : ''}"><g:message code="openart.issue.status.Completed" default="Completed" /></a></li>
                        <li class="block ${params.status == '4' ? 'selected' : ''}"><a href="?status=${4}${params.type ? '&type=' + params.type : ''}"><g:message code="openart.issue.status.Rejected" default="Rejected" /></a></li>
                    </ul>
                </div>
                <table class="list">
                    <thead>
                        <tr class="shadow">
                            <%-- We have to do these by hand, because g:sortableColumn doesn't pay attention to the query string's current data --%>
                            <th style="width: 10%" class="sortable${params.sort == 'id' ? ' sorted' + (params.order == 'asc' ? ' asc' : ' desc') : ''}"><a href="?sort=id&order=${params.order == 'asc' ? 'desc' : 'asc'}${params.type ? '&type=' + params.type : ''}${params.status ? '&status=' + params.status : ''}"><g:message code="openart.issue.id" default="ID" /></a></th>
                            <th style="width: 50%" class="sortable${params.sort == 'title' ? ' sorted' + (params.order == 'asc' ? ' asc' : ' desc') : ''}"><a href="?sort=title&order=${params.order == 'asc' ? 'desc' : 'asc'}${params.type ? '&type=' + params.type : ''}${params.status ? '&status=' + params.status : ''}"><g:message code="openart.issue.title" default="Issue" /></a></th>
                            <th style="width: 15%" class="sortable${params.sort == 'type' ? ' sorted' + (params.order == 'asc' ? ' asc' : ' desc') : ''}"><a href="?sort=type&order=${params.order == 'asc' ? 'desc' : 'asc'}${params.type ? '&type=' + params.type : ''}${params.status ? '&status=' + params.status : ''}"><g:message code="openart.issue.type" default="Issue type" /></a></th>
                            <th style="width: 15%" class="sortable${params.sort == 'status' ? ' sorted' + (params.order == 'asc' ? ' asc' : ' desc') : ''}"><a href="?sort=status&order=${params.order == 'asc' ? 'desc' : 'asc'}${params.type ? '&type=' + params.type : ''}${params.status ? '&status=' + params.status : ''}"><g:message code="openart.issue.status" default="Issue status" /></a></th>
                            <th style="width: 10%" class="sortable${params.sort == 'votes' ? ' sorted' + (params.order == 'asc' ? ' asc' : ' desc') : ''}"><a href="?sort=votes&order=${params.order == 'asc' ? 'desc' : 'asc'}${params.type ? '&type=' + params.type : ''}${params.votes ? '&votes=' + params.votes : ''}"><g:message code="openart.comment.plural" default="Comments" /></a></th>
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${issueList}" status="i" var="issue">
                        <tr class="${i % 2 == 0 ? 'even' : 'odd'}">
                            <td style="text-align: center;"><a href="${createLink(action: 'show', id: issue.id)}" style="display: block">
                                <g:if test="${issue.status == 3 || issue.status == 4}"><s>${issue.id}</s></g:if>
                                <g:else>${issue.id}</g:else>
                                </a></td>
                            <td>${issue.title.encodeAsHTML()}</td>
                            <td style="text-align: center;" class="${grailsApplication.config.openart.issue.type.repr[issue.type]}"><g:message code="openart.issue.type.${grailsApplication.config.openart.issue.type.repr[issue.type]}" default="${grailsApplication.config.openart.issue.type.repr[issue.type]}" /></td>
                            <td style="text-align: center;"><g:message code="openart.issue.status.${grailsApplication.config.openart.issue.status.repr[issue.status]}" default="${grailsApplication.config.openart.issue.status.repr[issue.status]}" /></td>
                            <td style="text-align: center;"><of:commentCountForObject object="${issue}" /></td>
                        </tr>
                        </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
