<html>
    <head>
        <title>Audio - ${audioUserObjectInstance.title.encodeAsHTML()}</title>
        <meta name="layout" value="main" />
    </head>
    <body>
        <div class="audioDisplay">
            <a href="${createLinkTo(dir: 'submissions/audio/' + audioUserObject.owner.username, file: audioUserObject.file)}">AUDIO FILE</a>
        </div>
        <g:render template="uo" />
    </body>
</html
