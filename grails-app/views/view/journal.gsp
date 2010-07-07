<html>
    <head>
        <title>Audio - ${audioUserObjectInstance.title.encodeAsHTML()}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="audioDisplay">
            <a href="${createLinkTo(dir: 'submissions/audio/' + audioUserObjectInstance.owner.username, file: audioUserObjectInstance.file)}">AUDIO FILE</a>
        </div>
        <g:render template="uo" />
    </body>
</html
