    <head>
        <title>Audio - ${instance.title.encodeAsHTML()}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="audioDisplay">
            <a href="${createLinkTo(dir: 'submissions/audio/' + instance.owner.username, file: instance.file)}">AUDIO FILE</a>
        </div>
        <g:render template="uo" />
    </body>
