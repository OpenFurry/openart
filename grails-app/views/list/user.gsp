<html>
    <head>
        <title>USER SUBMISSIONS</title><!-- TODO i18n -->
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="breadcrumbs">
            <g:link controller="person" action="show" params="[username: user.username]">~${user.username}</g:link> &raquo;
            SUBMISSIONS
        </div>
        <g:render template="/list" />
    </body>
</body>
