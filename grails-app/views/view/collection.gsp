<html>
    <head>
        <title>COLLECTION - ${instance.title}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:render template="uo" />
        <g:render template="/list" model="[uoList: instance.userObjects]" />
        <g:render template="/comments" model="[object: instance]" />
    </body>
</html>
