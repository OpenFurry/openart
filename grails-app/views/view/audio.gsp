<html>
    <head>
        <title>AUDIO - ${instance.title.encodeAsHTML()}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="audioDisplay">
            <a href="${resource(file: instance.file)}">AUDIO FILE</a>
        </div>
        <g:render template="uo" />
        <g:render template="/comments" model="[object: instance]" />
    </body>
</html>
