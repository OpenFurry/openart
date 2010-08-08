<html>
    <head>
        <title>Text - ${instance.title.encodeAsHTML()}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="textDisplay block">
            <markdown:renderHtml>${instance.text.encodeAsHTML()}</markdown:renderHtml>
        </div>
        <g:render template="uo" />
        <g:render template="/comments" model="[object: instance]" />
    </body>
</html>
