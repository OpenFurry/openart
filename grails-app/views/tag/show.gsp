<html>
    <head>
        <title><g:message code="openfurry.tag.view.show" default="Submissions tagged with {0}" args="[params.id]" /></title>
        <meta name="layout" content="main" />
    </head>
    <body>
    <g:isLoggedIn>
        <g:if test="${watched}">
        <g:link controller="watch" action="removeTag" params="[tag: params.id]">- UNWATCH TAG</g:link>
        </g:if>
        <g:else>
        <g:link controller="watch" action="addTag" params="[tag: params.id]">+ WATCH TAG</g:link>
        </g:else>
    </g:isLoggedIn>
    <g:render template="/list" />
    </body>
</html>
