<html><!-- TODO i18n -->
    <head>
        <title><g:if test="${group.exclusive}"><g:message code="openfurry.group.private" default="Private group" /></g:if><g:else><g:message code="openfurry.group" default="Group" /></g:else> - ${group.title}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="groupDescription block">${group.description}</div>
        <div class="groupEvents">
            <div class="shadow">EVENTS</div>
            <g:each in="${group.events}">
                ${it.title} - <g:formatDate date="${it.eventDateStart}" />
            </g:each>
        </div>
        <div class="groupPosts">
            <table class="list">
                <thead>
                    <tr>
                        <th style="width: 60%"><g:message code="openfurry.group.thread"  default="Thread" /></th>
                        <th style="width: 10%"><g:message code="openfurry.group.thread.replies" default="Replies" /></th>
                        <th style="width: 30%"><g:message code="openfurry.group.thread.owner" default="Poster" /></th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${group.posts}" var="post">
                        <tr>
                            <td><a href="${createLink(controller: 'group', action: 'thread', id: post.id)}">${post.title}</a></td>
                            <td><of:commentCountForObject object="${post}" /></td>
                            <td><of:linking noImages="true">${post.owner}</of:linking>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </div>
    </body>
</html>
