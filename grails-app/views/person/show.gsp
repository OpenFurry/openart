<html>
    <head>
        <meta name="layout" content="main" />
        <title>${person.userRealName.encodeAsHTML()} (~${person.username.encodeAsHTML()})</title>
    </head>

    <body>
        <div id="watchlink">
            <g:isLoggedIn>
                <g:if test="${watched}">
                    <g:link controller="watch" action="removeUser" params="[id: person.username]">- <g:message code="openfurry.watch.user.remove" default="Unwatch user" /></g:link>
                </g:if>
                <g:else>
                    <g:link controller="watch" action="addUser" params="[id: person.username]">+ <g:message code="openfurry.watch.user.add" default="Watch user" /></g:link>
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
                            <of:linking><markdown:renderHtml>${person.profile}</markdown:renderHtml></of:linking>
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
                        <td class="value">${person.username}</td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.userRealName" default="Display name" /></th>
                        <td class="value">${person.userRealName?.encodeAsHTML()}</td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.species" default="User's species" /></th>
                        <td class="value"><of:speciesString species="${person.species}" /></th>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.preferedLicense" default="Prefered license" /></th>
                        <td class="value"><g:if test="${person.preferedLicense}"><a href="${person.preferedLicense.url}" target="_blank">${person.preferedLicense.title} - ${person.preferedLicense.display}</a></g:if></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.type" default="User type" /></th>
                        <td class="value">${grailsApplication.config.openfurry.user.types.repr[person.memberType]}</td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.class" default="User class" /></th>
                        <td class="value">${grailsApplication.config.openfurry.user.classes.repr[person.memberClass]}</td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.preferedTheme" default="Prefered theme" /></th>
                        <td class="value">${person.preferedTheme?.id ?: "default"}</td>
                    </tr>
                    <g:if test="${loggedInUserInfo(field:'username').toString() == person.username}">
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.pennies" default="Pennies" /></th>
                        <td class="value">${person.pennies}</td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.user.warningLevel" default="Warning level" /></th>
                        <td class="value="><div style="background: url('${resource(dir: 'images', file: 'warning-gradient.jpg')}') top left no-repeat; width: 200px;"><div style="margin-left: ${person.warningLevel * 2}px"><strong>|</strong> (${person.warningLevel}%)</div></div></td>
                    </tr>
                    </g:if>
                </tbody>
            </table>
        </div>
        <div class="shadow" style="clear: both; width: 100%">USER SUBMISSIONS</div>
        <g:render template="/list" model="[uoList: submissions]" />
    </body>
</html>
