<head>
    <title>Application - ${instance.title.encodeAsHTML()}</title>
    <meta name="layout" content="main" />
</head>
<body>
    <div class="applicationDisplay">
        <img src="${resource(file: instance.screenShot)}" />
    </div>
    <g:render template="uo" />
</body>
