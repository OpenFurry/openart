<html><!-- TODO i18n -->
    <head>
        <title><g:message code="openfurry.group.thread.plural" default="Threads" /> - ${group.title}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="breadcrumbs">
            <g:link controller="group"><g:message code="openfurry.display.navigation.groups" default="Groups and events" /></g:link> &raquo;
            <g:link controller="group" action="show" id="${group.slug}">${group.title}</g:link> &raquo;
            <g:message code="openfurry.group.thread.plural" default="threads" />
        </div>
        <table class="list">
            <thead>
                <tr>
                    <th style="width: 60%"><g:message code="openfurry.group.thread"  default="Thread" /></th>
                    <th style="width: 10%"><g:message code="openfurry.group.thread.replies" default="Replies" /></th>
                    <th style="width: 30%"><g:message code="openfurry.group.thread.owner" default="Poster" /></th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${postList}" var="post" status="i">
                    <tr class="${i % 2 == 0 ? 'even' : 'odd'}">
                        <td>
                            <of:withPermission class="groups" permission="userCanRead" arg="${group}">
                                <a href="${createLink(controller: 'group', action: 'thread', id: post.id)}">
                            </of:withPermission>
                            ${post.title}
                            <of:withPermission class="groups" permission="userCanRead" arg="${group}">
                                </a>
                            </of:withPermission>
                        </td>
                        <td><of:commentCountForObject object="${post}" /></td>
                        <td><of:linking noImages="true">${post.owner}</of:linking>
                    </tr>
                </g:each>
                <g:if test="${postList.size() < 1}">
                    <tr>
                        <td colspan="3"><em>NO POSTS</td>
                    </tr>
                </g:if>
            </tbody>
        </table>
        <div class="paginateButtons">
            <g:paginate total="${params.totalPosts}" />
        </div>
        </div>
    </body>
</html>
