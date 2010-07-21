<html>
    <head>
        <title><g:message code="openfurry.tag.view.show" default="Submissions tagged with {0}" args="[params.id]" /></title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div id="watchlink">
            <g:isLoggedIn>
                <g:if test="${watched}">
                    <g:link controller="watch" action="removeTag" params="[id: params.id]">- <g:message code="openfurry.watch.tag.remove" default="Unwatch tag" /></g:link>
                </g:if>
                <g:else>
                    <g:link controller="watch" action="addTag" params="[id: params.id]">+ <g:message code="openfurry.watch.tag.add" default="Watch tag" /></g:link>
                </g:else>
            </g:isLoggedIn>
        </div>
        <g:render template="/list" />
    </body>
</html>
