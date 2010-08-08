<html>
    <head>
        <title>IMAGE - ${instance.title.encodeAsHTML()}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="imageDisplay">
            <a href="${resource(file: instance.fullFile)}">
                <img src="${resource(file: instance.sizedFile)}" />
            </a>
        </div>
        <g:render template="uo" />
        <g:render template="/comments" model="[object: instance]" />
    </body>
</html>
