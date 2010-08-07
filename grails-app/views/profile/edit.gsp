<html>
    <head>
        <!-- TODO i18n-->
        <title><g:message code="openfurry.user.views.edit" default="Edit profile" /></title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:hasErrors bean="${user}">
            <div class="errors">
                <g:renderErrors bean="${user}" as="list" />
            </div>
        </g:hasErrors>
        <g:uploadForm action="save" method="post">
            <table>
                <thead>
                    <tr>
                        <th colspan="2"><g:message code="openfurry.user.views.edit.info" default="Basic information" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.username" default="Username" /></th>
                        <td class="value">${user.username}</td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.userRealName" default="Display name" /></th>
                        <td class="value"><g:textField name="userRealName" value="${user.userRealName}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.email" default="Email" /></th>
                        <td class="value"><g:textField name="email" value="${user.email}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.password" default="Password" /></th>
                        <td class="value"><g:textField name="password" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.confirmPassword" default="Confirm password" /></th>
                        <td class="value"><g:textField name="pass" /></td>
                    </tr>
                </tbody>
                <thead>
                    <tr>
                        <th colspan="2"><g:message code="openfurry.user.views.edit.about" default="About you" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr clsas="prop">
                        <th class="name"><g:message code="openfurry.user.type" default="User type" /></th>
                        <td class=value">
                            <select name="memberType">
                                <g:each in="${0..9}" var="i">
                                    <option value="${i}"${user.memberType == i ? ' selected="selected"' : ''}><g:message code="openfurry.user.types.${grailsApplication.config.openfurry.user.types.repr[i]}" default="${grailsApplication.config.openfurry.user.types.repr[i]}" /></option>
                                </g:each>
                            </select>
                        </td>
                    </tr>
                    <tr class="prop">   
                        <th class="name"><g:message code="openfurry.user.species" default="Species" /></th>
                        <td class="value">
                            <select name="species.id">
                                <of:speciesOptions fromPerson="${user}" />
                            </select>
                        </td>
                    </tr>
                    <tr class="prop">
                        <th class="name">
                            <g:message code="openfurry.user.profile" default="Profile" />
                            <div class="message">
                                <p><g:link controller="flatpage" action="show" id="markdown"><g:message code="openfurry.messages.markdownOkay" default="Markdown allowed" /></g:link></p>
                                <p><g:link controller="flatpage" action="show" id="linking"><g:message code="openfurry.messages.linkingOkay" default="Linking allowed" /></g:link></p>
                            </div>
                        </th>
                        <td class="value"><g:textArea name="profile" rows="10" cols="75" value="${user.profile}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.avatar" default="Avatar" /></th>
                        <td class="value"><input type="file" name="av" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.commissionStatus" default="Accepting commissions" /></th>
                        <td class="value"><g:checkBox name="commissionStatus" value="${user.commissionStatus}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.tradeStatus" default="Accepting trades" /></th>
                        <td class="value"><g:checkBox name="tradeStatus" value="${user.tradeStatus}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.giftStatus" default="Accepting gift requests" /></th>
                        <td class="value"><g:checkBox name="giftStatus" value="${user.giftStatus}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.properties" default="Properties" /></th>
                        <td class="value"><g:link action="props"><g:message code="openfurry.user.properties.edit" default="Edit properties" /></g:link></td>
                    </tr>
                </tbody>
                <thead>
                    <tr>
                        <th colspan="2"><g:message code="openfurry.user.views.edit.preferences" default="Preferences" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.preferedLicense" default="Prefered license" /></th>
                        <td class="value">
                            <select name="preferedLicense.id">
                                <g:each in="${com.mjs_svc.openfurry.License.list()}">
                                    <option value="${it.id}"${it.id == user.preferedLicense.id ? " selected=\"selected\"" : ""}>${it.title}</option>
                                </g:each>
                            </select>
                        </td>
                    </tr>
                    <%--<tr class="prop">
                        <th class="name">PREFERED THEME</th>
                        <td cass="value"><!-- TODO --></td>
                    </tr>--%>
                </tbody>
            </table>
            <div class="buttons"><input type="submit" /></div>
        </g:uploadForm>
    </body>
</html>
