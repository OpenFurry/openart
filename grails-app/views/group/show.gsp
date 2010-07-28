<html><!-- TODO i18n -->
    <head>
        <title><g:if test="${group.exclusive}"><g:message code="openfurry.group.private" default="Private group" /></g:if><g:else><g:message code="openfurry.group" default="Group" /></g:else> - ${group.title}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="groupDescription block">
            <of:linking><markdown:renderHtml>${group.description}</markdown:renderHtml></of:linking>
            <hr />
            <g:if test="${of.hasPermission(class: 'groups', permission: 'userIsMember', arg: group)}">
                <g:link controller="group" action="leave" id="${group.slug}">LEAVE</g:link>
            </g:if>
            <g:else>
                <g:if test="${group.exclusive}">
                    <g:link controller="group" action="requestToJoin" id="${group.slug}">REQUEST</g:link>
                </g:if>
                <g:else>
                    <g:link controller="group" action="join" id="${group.slug}">JOIN</g:link>
                </g:else>
            </g:else>
        </div>
        <div class="groupEvents">
            <g:link controller="event" action="calendar" id="${group.slug}">CALENDAR</g:link>
            <of:withPermission class="groups" permission="userCanPost" arg="${group}">
                <div style="float:right"><g:link controller="event" action="create" id="${group.slug}">+ EVENT</g:link></div>
            </of:withPermission>
            <table class="list">
                <thead>
                    <tr>
                        <th style="width: 75%">EVENT</th>
                        <th style="width: 25%">DATE</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${events}" var="event" status="i">
                        <tr class="${i % 2 == 0 ? 'even' : 'odd'}">
                            <td>
                                <of:withPermission class="groups" permission="userCanRead" arg="${group}">
                                    <a href="${createLink(controller: 'event', action: 'show', id: event.id)}">
                                </of:withPermission>
                                ${event.title}
                                <of:withPermission class="groups" permission="userCanRead" arg="${group}">
                                    </a>
                                </of:withPermission>
                            </td>
                            <td><g:formatDate date="${event.eventDateStart}" format="dd, MMM yyyy h:mm a" /></td>
                        </tr>
                    </g:each>
                    <g:if test="${events.size() < 1}">
                        <tr>
                            <td colspan="2"><em>NO EVENTS</em></td>
                        </tr>
                    </g:if>
                </tbody>
            </table>
        </div>
        <div class="groupPosts">
            <g:link controller="group" action="posts" id="${group.slug}">POSTS</g:link>
            <of:withPermission class="groups" permission="userCanPost" arg="${group}">
                <div style="float: right"><g:link controller="group" action="post" id="${group.slug}">+ POST</g:link></div>
            </of:withPermission>
            <table class="list">
                <thead>
                    <tr>
                        <th style="width: 60%"><g:message code="openfurry.group.thread"  default="Thread" /></th>
                        <th style="width: 10%"><g:message code="openfurry.group.thread.replies" default="Replies" /></th>
                        <th style="width: 30%"><g:message code="openfurry.group.thread.owner" default="Poster" /></th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${posts}" var="post" status="i">
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
                    <g:if test="${posts.size() < 1}">
                        <tr>
                            <td colspan="3"><em>NO POSTS</em></td>
                        </tr>
                    </g:if>
                </tbody>
            </table>
        </div>
        <div class="groupMembers">
            <div class="shadow">MEMBERS</div>
            <g:each in="${group.members}" var="member">
                <div class="member${member.id == group.adminId ? ' admin' : ''}">
                    <of:linking>~${member.username}</of:linking>
                    <of:withPermission class="groups" permission="userIsAdmin" arg="${group}">
                        <g:link controller="group" action="transferAdmin" id="${group.slug}" params="[to: member.username]">MAKE SOLE ADMIN - CANNOT BE UNDONE</g:link>
                    </of:withPermission>
                </div>
            </g:each>
        </div>
    </body>
</html>
