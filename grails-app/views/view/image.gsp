    <head>
        <title>IMAGE - ${instance.title.encodeAsHTML()}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="imageDisplay">
            <a href="${createLinkTo(file: instance.fullFile)}">
                <img src="${createLinkTo(file: instance.sizedFile)}" />
            </a>
        </div>
        <g:render template="uo" />
    </body>
