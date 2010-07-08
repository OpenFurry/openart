<head>
    <meta name="layout" content="main" />
    <title><g:message code="openfurry.audioUO.view.create" default="New audio submission" /></title>
</head>

<body>
    <g:hasErrors bean="${instance}">
    <div class="errors">
        <g:renderErrors bean="${instance}" as="list" />
    </div>
    </g:hasErrors>
    <g:uploadForm action="saveAudio" method="post">
        <div class="block">
            <g:render template="uoform" />
            <table>
                <thead>
                    <tr>
                        <th colspan="2"><g:message code="openfurry.audioUO.sectionTitle" default="Audio submission details" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.audioUO.file" defualt="Audio file" /></th>
                        <td class="value">
                            <input type="file" name="file" /> <br />
                            <div class="message"><g:message code="openfurry.technical.allowedTypes" defualt="Allowed file types" /> ${grailsApplication.config.openfurry.fileTypes.audio}<br />
                                <g:message code="openfurry.technical.maxFileSize" default="Maximum file size" /> ${grailsApplication.config.openfurry.maxUploadSize.audio}MB
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><input type="submit" value="Create submission" /></span>
        </div>
    </g:uploadForm>
</body>
