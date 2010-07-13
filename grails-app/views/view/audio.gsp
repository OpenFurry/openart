    <head>
        <title>AUDIO - ${instance.title.encodeAsHTML()}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="audioDisplay">
            <a href="${createLinkTo(file: instance.file)}">AUDIO FILE</a>
        </div>
        <g:render template="uo" />
    </body>
