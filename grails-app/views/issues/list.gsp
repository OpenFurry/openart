<html>
    <head>
        <title><g:message code="openfurry.issue.plural" default="Issues" /></title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="verticalTabs">
            <ul class="nav">
                <li class="block ${params.type ? '' : 'selected'}"><a href="${createLink(controller: params.controller, action: params.action, id: params.id)}${params.status ? '?status=' + params.status : ''}"><g:message code="openfurry.issue.types.all" default="All" /></a></li>
                <li class="block ${params.type == '0' ? 'selected' : ''}"><a href="${createLink(controller: params.controller, action: params.action, id: params.id)}?type=0${params.status ? '&status=' + params.status : ''}"><g:message code="openfurry.issue.types.Bug" default="Bug" /></a></li>
                <li class="block ${params.type == '1' ? 'selected' : ''}"><a href="${createLink(controller: params.controller, action: params.action, id: params.id)}?type=1${params.status ? '&status=' + params.status : ''}"><g:message code="openfurry.issue.types.Improvement" default="Improvement" /></a></li>
                <li class="block ${params.type == '2' ? 'selected' : ''}"><a href="${createLink(controller: params.controller, action: params.action, id: params.id)}?type=2${params.status ? '&status=' + params.status : ''}"><g:message code="openfurry.issue.types.NewFeature" default="New Feature Request" /></a></li>
            </ul>
            <div class="content block" style="min-height: 20em;">
                <div class="horizontalTabs">
                    <ul class="nav">
                        <li class="block ${params.status ? '' : 'selected'}"><a href="${createLink(controller: params.controller, action: params.action, id: params.id)}${params.type ? '?type=' + params.type : ''}"><g:message code="openfurry.issue.status.all" default="All" /></a></li>
                        <li class="block ${params.status == '0' ? 'selected' : ''}"><a href="${createLink(controller: params.controller, action: params.action, id: params.id)}?status=${0}${params.type ? '&type=' + params.type : ''}"><g:message code="openfurry.issue.status.Suggestion" default="Suggestion" /></a></li>
                        <li class="block ${params.status == '1' ? 'selected' : ''}"><a href="${createLink(controller: params.controller, action: params.action, id: params.id)}?status=${1}${params.type ? '&type=' + params.type : ''}"><g:message code="openfurry.issue.statusSeconded" default="Seconded" /></a></li>
                        <li class="block ${params.status == '2' ? 'selected' : ''}"><a href="${createLink(controller: params.controller, action: params.action, id: params.id)}?status=${2}${params.type ? '&type=' + params.type : ''}"><g:message code="openfurry.issue.status.Accepted" default="Accepted" /></a></li>
                        <li class="block ${params.status == '3' ? 'selected' : ''}"><a href="${createLink(controller: params.controller, action: params.action, id: params.id)}?status=${3}${params.type ? '&type=' + params.type : ''}"><g:message code="openfurry.issue.status.Completed" default="Completed" /></a></li>
                        <li class="block ${params.status == '4' ? 'selected' : ''}"><a href="${createLink(controller: params.controller, action: params.action, id: params.id)}?status=${4}${params.type ? '&type=' + params.type : ''}"><g:message code="openfurry.issue.status.Rejected" default="Rejected" /></a></li>
                    </ul>
                </div>
                <div style="float:right">
                    <strong><a href="${createLink(action: 'create')}">+ <g:message code="openfurry.issue.views.create" default="Create issue" /></a></strong>
                </div>
                <table>
                    <thead>
                        <tr class="shadow">
                            <th style="width: 10%"><g:message code="openfurry.issue.id" default="ID" /></th>
                            <th style="width: 50%"><g:message code="openfurry.issue.title" default="Issue" /></th>
                            <th style="width: 15%"><g:message code="openfurry.issue.type" default="Issue type" /></th>
                            <th style="width: 15%"><g:message code="openfurry.issue.status" default="Issue status" /></th>
                            <th style="width: 10%"><g:message code="openfurry.issue.votes" default="Votes" /></th>
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${issueList}" status="i" var="issue">
                        <tr class="${i % 2 == 0 ? 'even' : 'odd'}">
                            <td><a href="${createLink(action: 'show', id: issue.id)}" style="display: block">
                                <g:if test="${issue.status == 3 || issue.status == 4}"><s>${issue.id}</s></g:if>
                                <g:else>${issue.id}</g:else>
                                </a></td>
                            <td>${issue.title.encodeAsHTML()}</td>
                            <td class="${grailsApplication.config.openfurry.issue.type.repr[issue.type]}"><g:message code="openfurry.issue.type.${grailsApplication.config.openfurry.issue.type.repr[issue.type]}" default="${grailsApplication.config.openfurry.issue.type.repr[issue.type]}" /></td>
                            <td><g:message code="openfurry.issue.status.${grailsApplication.config.openfurry.issue.status.repr[issue.status]}" default="${grailsApplication.config.openfurry.issue.status.repr[issue.status]}" /></td>
                            <td>${issue.votes}</td>
                        </tr>
                        </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
