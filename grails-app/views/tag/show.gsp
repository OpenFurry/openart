<html>
    <head>
        <title>TAG</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:each in="${uoList}">
            <li><a href="${createLink(controller: 'view', action: 'show', id: it.id)}">${it.title}</li>
        </g:each>
    </body>
</html>
