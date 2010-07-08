<head>
    <title>Application - ${instance.title.encodeAsHTML()}</title>
    <meta name="layout" content="main" />
</head>
<body>
    <div class="applicationDisplay">
        <a href="${createLinkTo(dir: 'submissions/application/' + instance.owner.username, file: instance.screenshot)}">APPLICATION SCREEN SHOT</a>
    </div>
    <g:render template="uo" />
</body>
