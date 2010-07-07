<head>
    <meta name="layout" content="main" />
    <title>New Submission</title>
</head>

<body>
    <g:form action="chooseType">
        <div class="dialog">
            <select name="type" id="type">
                <option selected="selected"><strong>--Select submission type--</strong></option>
                <option value="audio">Audio</option>
                <option value="video">Video</option>
                <option value="flash">Flash</option>
                <option value="image">Image</option>
                <option value="text">Text</option>
                <option value="journal">Journal</option>
                <option value="application">Program/Website/Application</option>
            </select>
        </div>
        <div class="buttons">
            <span class="button"><input type="submit" value="Continue to submission" /></span>
        </div>
    </g:form>
</body>
