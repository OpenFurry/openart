<head>
    <title>UNODERED COLLECTION - ${instance.title.encodeAsHTML()}</title>
    <meta name="layout" content="main" />
</head>
<body>
    <div class="collectionDisplay block">
        <img src="${createLinkTo(file: instance.thumbnail)}" />
    </div>
        <g:render template="uo" />
</body>
