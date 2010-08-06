<html>
    <head>
        <title>FRIENDS OF ${user.userRealName} (~${user.username})</title><!-- TODO i18n -->
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="breadcrumbs">
            <g:link controller="user" action="show" params="[username: user.username]">~${user.username}</g:link> &raquo;
            FRIENDS
        </div>
        <ul>
            <g:each in="${user.friends}" var="friend">
                <li>
                    <of:linking>${friend}</of:linking>
                    <g:if test="${user.id == loggedInUserInfo(field: 'id')}">
                        <br />
                        <g:link controller="watch" action="unfriend" id="${friend.id}">- REMOVE</g:link>
                    </g:if>
                </li>
            </g:each>
        </ul>
    </body>
</html>
