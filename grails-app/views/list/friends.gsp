<html>
    <head>
        <title>USER'S FRIENDS' SUBMISSIONS</title><!-- TODO i18n -->
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="breadcrumbs">
            <g:link controller="user" action="show" params="[username: user.username]">~${user.username}</g:link> &raquo;
            <g:link controller="user" action="friends" id="${user.username}">FRIENDS</g:link> &raquo;
            SUBMISSIONS
        </dive
        <g:render template="/list" />
    </body>
</html>
