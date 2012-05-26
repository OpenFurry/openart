<html>
    <head>
        <title><g:message code="openart.group.plural" default="Groups" /></title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <table class="list">
            <thead>
                <tr>
                    <th style="width: 60%">TITLE</th><!-- TODO i18n -->
                    <th style="width: 20%">CATEGORY</th>
                    <th style="width: 20%">ADMIN</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${groups}" var="group" status="count">
                    <tr class="${count % 2 == 0 ? 'even' : 'odd' } ${group.exclusive ? 'groupPrivate' : 'groupPublic'}">
                        <td><a href="${createLink(controller: 'group', action: 'show', id: group.slug)}">${group.title.encodeAsHTML()} <g:if test="${group.exclusive}">[<g:message code="openart.group.private" default="Private group" />]</g:if></a></td>
                        <td><g:message code="openart.group.category.${grailsApplication.config.openart.group.category.repr[group.category]}" default="${grailsApplication.config.openart.group.category.repr[group.category]}" /></td>
                        <td><of:linking noImages="true">${openart.Person.get(group.adminId)}</of:linking></td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </body>
</html>
