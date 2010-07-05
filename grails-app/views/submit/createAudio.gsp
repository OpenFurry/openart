<head>
    <meta name="layout" content="main" />
    <title>New Audio Submission</title>
</head>

<body>
    <g:hasErrors bean="${instance}">
    <div class="errors">
        <g:renderErrors bean="${instance}" as="list" />
    </div>
    </g:hasErrors>
    <g:uploadForm action="saveAudio" method="post">
        <div class="dialog">
            <g:render template="uoform" />
            <table>
                <thead>
                    <tr>
                        <th colspan="2">Audio submission details</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name">Audio file</th>
                        <td class="value"><input type="file" name="file" /></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><input type="submit" value="Create submission" /></span>
        </div>
    </g:uploadForm>
</body>
