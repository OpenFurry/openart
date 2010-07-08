    <head>
        <title>Video - ${instance.title.encodeAsHTML()}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="videoDisplay">
            <a href="${createLinkTo(dir: 'submissions/video/' + instance.owner.username, file: instance.file)}">VIDEO FILE</a>
        </div>
        <g:render template="uo" />
    </body>
