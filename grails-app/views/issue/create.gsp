<html>
    <head>
        <title><g:message code="openart.issue.views.creat" default="Create issue" /></title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:hasErrors bean="${instance}">
        <div class="errors">
            <g:renderErrors bean="${instance}" as="list" />
        </div>
        </g:hasErrors>
        <g:form action="save" method="post">
            <g:if test="${params.id}"><input type="hidden" name="id" value="${params.id}" /></g:if>
            <table>
                <thead>
                    <tr>
                        <th colspan="2">ISSUE DETAILS<!-- TODO i18n --></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <td class="name"><g:message code="openart.issue.title" default="Issue title" /></td>
                        <td class="value ${hasErrors(bean: instance, field: 'title', 'errors')}"><g:textField name="title" value="${instance?.title}" /></td>
                    </tr>
                    <tr class="prop">
                        <td class="name"><g:message code="openart.issue.type" default="Issue type" /></td>
                        <td class="value ${hasErrors(bean: instance, field: 'type', 'errors')}">
                            <select name="type">
                                <option value="0"${instance?.type == 0 ? ' selected="selected"':''}>${message(code: "openart.issue.type." + grailsApplication.config.openart.issue.repr[0], default: "Bug")}</option>
                                <option value="1"${instance?.type == 1 ? ' selected="selected"':''}>${message(code: "openart.issue.type." + grailsApplication.config.openart.issue.repr[1], default: "Improvement")}</option>
                                <option value="2"${instance?.type == 2 ? ' selected="selected"':''}>${message(code: "openart.issue.type." + grailsApplication.config.openart.issue.repr[2], default: "New Feature")}</option>
                            </select>
                        </td>
                    </tr>
                    <g:ifAnyGranted role="ROLE_ADMIN,ROLE_STAFF,ROLE_GOVERNOR">
                        <tr class="prop">
                            <td class="name"><g:message code="openart.issue.status" default="Issue status" /></td>
                            <td class="value ${hasErrors(bean: instance, field: 'type', 'errors')}">
                                <select name="status">
                                    <option value="0"${instance?.status == 0 ? ' selected="selected"':''}>${message(code: "openart.issue.status." + grailsApplication.config.openart.issue.status.repr[0], defualt: "Suggestion")}</option>
                                    <option value="1"${instance?.status == 1 ? ' selected="selected"':''}>${message(code: "openart.issue.status." + grailsApplication.config.openart.issue.status.repr[1], defualt: "Seconded")}</option>
                                    <option value="2"${instance?.status == 2 ? ' selected="selected"':''}>${message(code: "openart.issue.status." + grailsApplication.config.openart.issue.status.repr[2], defualt: "Accepted")}</option>
                                    <option value="3"${instance?.status == 3 ? ' selected="selected"':''}>${message(code: "openart.issue.status." + grailsApplication.config.openart.issue.status.repr[3], defualt: "Completed")}</option>
                                    <option value="4"${instance?.status == 4 ? ' selected="selected"':''}>${message(code: "openart.issue.status." + grailsApplication.config.openart.issue.status.repr[4], defualt: "Rejected")}</option>
                                </select>
                            </td>
                        </tr>
                    </g:ifAnyGranted>
                    <tr class="prop">
                        <td class="name"><g:message code="openart,issue.description" default="Description" /></td>
                        <td class="value ${hasErrors(bean: instance, field: 'description', 'errors')}"><g:textArea name="description" rows="10" cols="75" value="${instance?.description}"/></td>
                    </tr>
                </tbody>
            </table>
            <div class="shadow buttons"><input type="submit" /></div>
        </g:form>
    </body>
</html>
