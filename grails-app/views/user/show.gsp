<html>
    <head>
        <meta name="layout" content="main" />
        <title>${user.userRealName.encodeAsHTML()} (~${user.username.encodeAsHTML()})</title>
    </head>

    <body>
        <g:ifAnyGranted role="ROLE_ADMIN,ROLE_STAFF">
            <g:render template="admin" />
        </g:ifAnyGranted>
        <div id="watchlink">
            <g:isLoggedIn>
                <g:if test="${loggedInUserInfo(field: 'username') != user.username}">
                    <g:if test="${watched}">
                        <g:link controller="watch" action="removeUser" params="[id: user.username]">- <g:message code="openfurry.watch.user.remove" default="Unwatch user" /></g:link>
                    </g:if>
                    <g:else>
                        <g:link controller="watch" action="addUser" params="[id: user.username]">+ <g:message code="openfurry.watch.user.add" default="Watch user" /></g:link>
                    </g:else>
                </g:if>
                <g:else>
                    <g:message code="openfurry.user.thatsyou" default="(That's you!)" />
                </g:else>
            </g:isLoggedIn>
        </div>
        <div class="block" style="width: 69%; float: left">
            <table>
                <thead>
                    <tr>
                        <th class="shadow">
                            <g:message code="openfurry.user.profile" default="Profile" />
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <of:linking><markdown:renderHtml>${user.profile}</markdown:renderHtml></of:linking>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="block" style="width: 30%; float: right;">
            <table>
                <thead>
                    <tr>
                        <th colspan="2" class="shadow"><g:message code="openfurry.user.details" default="User details" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.username" default="Username" /></th>
                        <td class="value">${user.username}</td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.userRealName" default="Display name" /></th>
                        <td class="value">${user.userRealName?.encodeAsHTML()}</td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.species" default="User's species" /></th>
                        <td class="value"><of:speciesString species="${user.species}" /></th>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.preferedLicense" default="Prefered license" /></th>
                        <td class="value"><g:if test="${user.preferedLicense}"><a href="${user.preferedLicense.url}" target="_blank">${user.preferedLicense.title} - ${user.preferedLicense.display}</a></g:if></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.type" default="User type" /></th>
                        <td class="value">${grailsApplication.config.openfurry.user.types.repr[user.memberType]}</td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.class" default="User class" /></th>
                        <td class="value">${grailsApplication.config.openfurry.user.classes.repr[user.memberClass]}</td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.preferedTheme" default="Prefered theme" /></th>
                        <td class="value">${user.preferedTheme?.id ?: "default"}</td>
                    </tr>
                    <g:if test="${loggedInUserInfo(field:'username').toString() == user.username}">
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.pennies" default="Pennies" /></th>
                        <td class="value">${user.pennies}</td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.warningLevel" default="Warning level" /></th>
                        <td class="value="><div style="background: url('${resource(dir: 'images', file: 'warning-gradient.jpg')}') top left no-repeat; width: 200px;"><div style="margin-left: ${user.warningLevel * 2}px"><strong>|</strong> (${user.warningLevel}%)</div></div></td>
                    </tr>
                    </g:if>
                </tbody>
                <thead>
                    <tr>
                        <th colspan="2">USER PROPERTIES<!-- TODO i18n --></th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${user.props}">
                        <of:userProperty key="${it.key}" value="${it.value}" />
                    </g:each>
                </tbody>
            </table>
        </div>
        <div class="shadow" style="clear: both; width: 100%">USER SUBMISSIONS</div>
        <g:render template="/list" model="[uoList: submissions]" />
    </body>
</html>
