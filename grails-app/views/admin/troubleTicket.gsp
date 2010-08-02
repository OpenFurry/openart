<html><!-- TODO i18n -->
    <head>
        <title>TROUBLE TICKET</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:hasErrors bean="${instance}">
            <div class="errors">
                <g:renderErrors bean="${instance}" as="list" />
            </div>
        </g:hasErrors>
        <g:form method="post" action="troubleTicket">
            <g:if test="${params.id}"><input type="hidden" name="id" value="${params.id}" /></g:if>
            <table>
                <thead>
                    <tr>
                        <th colspan="2">TICKET DETAILS</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <td class="name"><g:message code="openfurry.issue.title" default="Issue title" /></td>
                        <td class="value ${hasErrors(bean: instance, field: 'title', 'errors')}"><g:textField name="title" value="${instance?.title}" /></td>
                    </tr>
                    <tr class="prop">
                        <td class="name"><g:message code="openfurry.issue.type" default="Issue type" /></td>
                        <td class="value ${hasErrors(bean: instance, field: 'type', 'errors')}">
                            <select name="type">
                                <option value="3"${instance?.type == 3 ? ' selected="selected"':''}>${message(code: "openfurry.issue.type." + grailsApplication.config.openfurry.issue.repr[3], default: "ToS Violation")}</option>
                                <option value="4"${instance?.type == 4 ? ' selected="selected"':''}>${message(code: "openfurry.issue.type." + grailsApplication.config.openfurry.issue.repr[4], default: "AUP Violation")}</option>
                                <option value="5"${instance?.type == 5 ? ' selected="selected"':''}>${message(code: "openfurry.issue.type." + grailsApplication.config.openfurry.issue.repr[5], default: "Account Issue")}</option>
                                <option value="6"${instance?.type == 6 ? ' selected="selected"':''}>${message(code: "openfurry.issue.type." + grailsApplication.config.openfurry.issue.repr[6], default: "Harrassment")}</option>
                            </select>
                        </td>
                    </tr>
                    <g:ifAnyGranted role="ROLE_ADMIN,ROLE_STAFF,ROLE_GOVERNOR">
                        <tr class="prop">
                            <td class="name"><g:message code="openfurry.issue.status" default="Issue status" /></td>
                            <td class="value ${hasErrors(bean: instance, field: 'type', 'errors')}">
                                <select name="status">
                                    <option value="0"${instance?.status == 0 ? ' selected="selected"':''}>${message(code: "openfurry.issue.status." + grailsApplication.config.openfurry.issue.status.repr[0], defualt: "Suggestion")}</option>
                                    <option value="1"${instance?.status == 1 ? ' selected="selected"':''}>${message(code: "openfurry.issue.status." + grailsApplication.config.openfurry.issue.status.repr[1], defualt: "Seconded")}</option>
                                    <option value="2"${instance?.status == 2 ? ' selected="selected"':''}>${message(code: "openfurry.issue.status." + grailsApplication.config.openfurry.issue.status.repr[2], defualt: "Accepted")}</option>
                                    <option value="3"${instance?.status == 3 ? ' selected="selected"':''}>${message(code: "openfurry.issue.status." + grailsApplication.config.openfurry.issue.status.repr[3], defualt: "Completed")}</option>
                                    <option value="4"${instance?.status == 4 ? ' selected="selected"':''}>${message(code: "openfurry.issue.status." + grailsApplication.config.openfurry.issue.status.repr[4], defualt: "Rejected")}</option>
                                </select>
                            </td>
                        </tr>
                    </g:ifAnyGranted>
                    <tr class="prop">
                        <td class="name"><g:message code="openfurry,issue.description" default="Description" /></td>
                        <td class="value ${hasErrors(bean: instance, field: 'description', 'errors')}"><g:textArea name="description" rows="10" cols="75" value="${instance?.description}"/></td>
                    </tr>
                </tbody>
            </table>
            <div class="shadow buttons"><input type="submit" /></div>
        </g:form>
    </body>
</html>
