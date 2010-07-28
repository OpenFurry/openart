<html><!-- TODO i18n -->
    <head>
        <title><g:message code="openfurry.group.thread" default="Thread" /> - ${thread.title}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="breadcrumbs">
            <g:link controller="group"><g:message code="openfurry.display.navigation.groups" default="Groups and events" /></g:link> &raquo;
            <g:link controller="group" action="show" id="${thread.group.slug}">${thread.group.title}</g:link> &raquo;
            <g:link controller="group" action="threads" id="${thread.group.slug}"><g:message code="openfurry.group.thread.plural" default="threads" /></g:link> &raquo;
            ${thread.title}
        </div>
        <div class="groupPost block">
            ${thread.post}
        </div>
        <g:render template="/comments" model="[object: thread]" />
    </body>
</html>
