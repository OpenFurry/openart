<html>
    <head>
        <!-- TODO i18n-->
        <title>EDIT PROFILE</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:form action="save" method="post">
            <table>
                <thead>
                    <tr>
                        <th colspan="2">BASIC INFORMATION</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name">USERNAME</th>
                        <td class="value">${person.username}</td>
                    </tr>
                    <tr class="prop">
                        <th class="name">DISPLAY NAME</th>
                        <td class="value"><g:textField name="userRealName" value="${person.userRealName}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name">EMAIL</th>
                        <td class="value"><g:textField name="email" value="${person.email}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name">PASSWORD</th>
                        <td class="value"><g:textField name="passwd" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name">CONFIRM PASSWORD</th>
                        <td class="value"><g:textField name="pass" /></td>
                    </tr>
                </tbody>
                <thead>
                    <tr>
                        <th colspan="2">ABOUT YOU</th>
                    </tr>
                </thead>
                <tbody>
                    <tr clsas="prop">
                        <th class="name">MEMBER TYPE</th>
                        <td class=value">
                            <select name="memberType">
                                <option value="0"><g:message code="openfurry.user.types.${grailsApplication.config.openfurry.user.types.repr[0]}" default="${grailsApplication.config.openfurry.user.types.repr[0]}" /></option>
                                <option value="1"><g:message code="openfurry.user.types.${grailsApplication.config.openfurry.user.types.repr[1]}" default="${grailsApplication.config.openfurry.user.types.repr[1]}" /></option>
                                <option value="2"><g:message code="openfurry.user.types.${grailsApplication.config.openfurry.user.types.repr[2]}" default="${grailsApplication.config.openfurry.user.types.repr[2]}" /></option>
                                <option value="3"><g:message code="openfurry.user.types.${grailsApplication.config.openfurry.user.types.repr[3]}" default="${grailsApplication.config.openfurry.user.types.repr[3]}" /></option>
                                <option value="4"><g:message code="openfurry.user.types.${grailsApplication.config.openfurry.user.types.repr[4]}" default="${grailsApplication.config.openfurry.user.types.repr[4]}" /></option>
                                <option value="5"><g:message code="openfurry.user.types.${grailsApplication.config.openfurry.user.types.repr[5]}" default="${grailsApplication.config.openfurry.user.types.repr[5]}" /></option>
                                <option value="6"><g:message code="openfurry.user.types.${grailsApplication.config.openfurry.user.types.repr[6]}" default="${grailsApplication.config.openfurry.user.types.repr[6]}" /></option>
                                <option value="7"><g:message code="openfurry.user.types.${grailsApplication.config.openfurry.user.types.repr[7]}" default="${grailsApplication.config.openfurry.user.types.repr[7]}" /></option>
                                <option value="8"><g:message code="openfurry.user.types.${grailsApplication.config.openfurry.user.types.repr[8]}" default="${grailsApplication.config.openfurry.user.types.repr[8]}" /></option>
                                <option value="9"><g:message code="openfurry.user.types.${grailsApplication.config.openfurry.user.types.repr[9]}" default="${grailsApplication.config.openfurry.user.types.repr[9]}" /></option>
                            </select>
                        </td>
                    </tr>
                    <tr class="prop">   
                        <th class="name">SPECIES</th>
                        <td class="value">
                            <select name="species">
                                <of:speciesOptions />
                            </select>
                        </td><!-- TODO: pass species to tag, make it selected -->
                    </tr>
                </tbody>
            </table>
        </g:form>
    </body>
</html>
