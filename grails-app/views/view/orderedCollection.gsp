<html>
    <head>
        <title>UNODERED COLLECTION - ${instance.title.encodeAsHTML()}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:render template="uo" />
        <div class="collectionDisplay block">
            <g:render template="/list" model="[uoList: instance.userObjects]" />
        </div>
        <g:render template="/comments" model="[object: instance]" />
    </body>
</html>
