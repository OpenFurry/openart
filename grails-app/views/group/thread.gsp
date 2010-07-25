<html><!-- TODO i18n -->
    <head>
        <title><g:message code="openfurry.group.thread" default="Thread" /> - ${thread.title}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="groupPost block">
            ${thread.post}
        </div>
        <g:render template="/comments" model="[object: thread]" />
    </body>
</html>
