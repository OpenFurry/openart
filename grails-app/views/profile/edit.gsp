<html>
    <head>
        <!-- TODO i18n-->
        <title>EDIT PROFILE</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:hasErrors bean="${person}">
            <div class="errors">
                <g:renderErrors bean="${person}" as="list" />
            </div>
        </g:hasErrors>
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
                        <td class="value"><g:textField name="password" /></td>
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
                                <g:each in="${0..9}" var="i">
                                <option value="${i}"${person.memberType == i ? ' selected="selected"' : ''}><g:message code="openfurry.user.types.${grailsApplication.config.openfurry.user.types.repr[i]}" default="${grailsApplication.config.openfurry.user.types.repr[i]}" /></option>
                                </g:each>
                            </select>
                        </td>
                    </tr>
                    <tr class="prop">   
                        <th class="name">SPECIES</th>
                        <td class="value">
                            <select name="newspecies">
                                <of:speciesOptions fromPerson="${person}" />
                            </select>
                        </td><!-- TODO: pass species to tag, make it selected -->
                    </tr>
                    <tr class="prop">
                        <th class="name">PROFILE</th>
                        <td class="value"><g:textArea name="profile" rows="10" cols="75" value="${person.profile}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name">AVATAR</th>
                        <td class="value"><!-- TODO --></td>
                    </tr>
                    <tr class="prop">
                        <th class="name">ACCPETING COMMISSIONS</th>
                        <td class="value"><g:checkBox name="commissionStatus" value="${person.commissionStatus}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name">ACCEPTING TRADES</th>
                        <td class="value"><g:checkBox name="tradeStatus" value="${person.tradeStatus}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name">ACCEPTING GIFT REQUESTS</th>
                        <td class="value"><g:checkBox name="giftStatus" value="${person.giftStatus}" /></td>
                    </tr>
                </tbody>
                <thead>
                    <tr>
                        <th colspan="2">PREFERENCES</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name">PREFERED LICENSE</th>
                        <td class="value"><!-- TODO --></td>
                    </tr>
                    <%--<tr class="prop">
                        <th class="name">PREFERED THEME</th>
                        <td cass="value"><!-- TODO --></td>
                    </tr>--%>
                </tbody>
            </table>
            <div class="buttons"><input type="submit" /></div>
        </g:form>
    </body>
</html>
