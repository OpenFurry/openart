<html>
    <head>
        <title>Flash - ${instance.title.encodeAsHTML()}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="flashDisplay">
            <a href="${resource(dir: 'submissions/flash/' + instance.owner.username, file: instance.file)}">FLASH</a>
        </div>
        <g:render template="uo" />
        <g:render template="/comments" model="[object: instance]" />
    </body>
</html>
