dd
<head>
    <meta name="layout" content="main" />
    <title>Submit</title>
</head>

<body>
    <h2>New submission</h2>
    <g:form action="chooseType">
        <div class="dialog">
            <select name="type" id="type">
                <option selected="selected"><strong>--Select submission type--</strong></option>
                <option value="createAudio">Audio</option>
                <option value="createVideo">Video</option>
                <option value="createFlash">Flash</option>
                <option value="createImage">Image</option>
                <option value="createText">Text</option>
                <option value="createJournal">Journal</option>
                <option value="createApplication">Program/Website/Application</option>
            </select>
        </div>
        <div class="buttons">
            <span class="button"><input type="submit" value="Continue to submission" /></span>
        </div>
    </g:form>
</body>
