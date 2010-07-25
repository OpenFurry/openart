<html><!-- TODO i18n -->
    <head>
        <title>THREAD</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="groupPost block">
            <div class="shadow">${thread.title}</div>
            ${thread.post}
        </div>
        <g:render template="/comments" model="[object: thread]" />
    </body>
</html>
