<html>
    <head>
        <title><g:message code="openfurry.tag.list" default="Tag Cloud" /></title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:each in="${tagCloud}">
        <a href="${createLink(controller: 'tag', action: 'show', id: it.tag.tag)}" style="font-size: ${it.fontSize * 10 + 70}%">${it.tag.tag}</a>
        </g:each>
    </body>
</html>
