<html>
    <head>
        <title>Journal - ${instance.title.encodeAsHTML()}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="journalDisplay block">
            <of:linking>${instance.owner}</of:linking>
            <hr />
            <of:linking><markdown:renderHtml>${instance.text.encodeAsHTML()}</markdown:renderHtml></of:linking>
        </div>
        <g:render template="/comments" model="[object: instance]" />
    </body>
</html>
