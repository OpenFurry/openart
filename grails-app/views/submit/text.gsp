<head>
    <meta name="layout" content="main" />
    <title><g:message code="openfurry.textUO.view.create" default="New text submission" /></title>
</head>

<body>
    <g:hasErrors bean="${instance}">
    <div class="errors">
        <g:renderErrors bean="${instance}" as="list" />
    </div>
    </g:hasErrors>
    <g:uploadForm action="saveText" method="post">
        <div class="dialog">
            <g:render template="uoform" />
            <table>
                <thead>
                    <tr>
                        <th colspan="2"><g:message code="openfurry.textUO.sectionTitle" default="Text submission details" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name">
                            <g:message code="openfurry.textUO.text" default="Full text" />
                            <div class="message">
                                <p><g:link controller="flatpage" action="show" id="markdown"><g:message code="openfurry.messages.markdownOkay" default="Markdown allowed" /></g:link></p>
                                <p><g:link controller="flatpage" action="show" id="linking"><g:message code="openfurry.messages.linkingOkay" default="Linking allowed" /></g:link></p>
                            </div>
                        </th>
                        <td class="value"><g:textArea name="text" rows="10" cols="75" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.textUO.attachment" default="Text file" /></th>
                        <td class="value">
                            <g:if test="${instance?.attachmentFile}"><g:message code="openfurry.technical.currentAttachment" default="Current attachment:" /> ${instance.attachmentFile}</g:if>
                            <input type="file" name="attachment" />
                            <div class="message"><g:message code="openfurry.technical.allowedTypes" default="Allowed file types" /> ${grailsApplication.config.openfurry.fileTypes.text}<br />
                                <g:message code="openfurry.technical.maxFileSize" default="Maximum file size" /> ${grailsApplication.config.openfurry.maxUploadSize.text}MB
                                <g:if test="${instance?.attachmentFile}"><br /><g:message code="openfurry.technical.uploadWillReplace" default="Uploading a new file will replace the old one" /></g:if>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><input type="submit" value="${g.message(code: 'openfurry.uo.submit', default: 'Create submission')}" /></span>
        </div>
    </g:uploadForm>
</body>
