<html>
    <head>
        <title>IMAGE - ${instance.title.encodeAsHTML()}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="imageDisplay">
            <a href="${createLinkTo(dir: 'submissions/image/' + instance.owner.username, file: instance.fullFile)}">IMAGE FILE</a>
        </div>
        <g:render template="uo" />
    </body>
</html
