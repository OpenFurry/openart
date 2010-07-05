<head>
    <meta name="layout" content="main" />
    <title>New Audio Submission</title>
</head>

<body>
    <h2>New Audio Submission</h2>
    <g:form action="saveAudio">
        <div class="dialog">
            <g:render template="uoform" />
            <em>Audio specific form goes here</em>
        </div>
        <div class="buttons">
            <span class="button"><input type="submit" value="Create submission" /></span>
        </div>
    </g:form>
</body>
