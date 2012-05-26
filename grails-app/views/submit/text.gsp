<head>
    <meta name="layout" content="main" />
    <title><g:message code="openart.textUO.view.create" default="New text submission" /></title>
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
                        <th colspan="2"><g:message code="openart.textUO.sectionTitle" default="Text submission details" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name">
                            <g:message code="openart.textUO.text" default="Full text" />
                            <div class="message">
                                <p><g:link controller="flatpage" action="show" id="markdown"><g:message code="openart.messages.markdownOkay" default="Markdown allowed" /></g:link></p>
                                <p><g:link controller="flatpage" action="show" id="linking"><g:message code="openart.messages.linkingOkay" default="Linking allowed" /></g:link></p>
                            </div>
                        </th>
                        <td class="value"><g:textArea name="text" rows="10" cols="75" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openart.textUO.attachment" default="Text file" /></th>
                        <td class="value">
                            <g:if test="${instance?.attachmentFile}"><g:message code="openart.technical.currentAttachment" default="Current attachment:" /> ${instance.attachmentFile}</g:if>
                            <input type="file" name="attachment" />
                            <div class="message"><g:message code="openart.technical.allowedTypes" default="Allowed file types" /> ${grailsApplication.config.openart.fileTypes.text}<br />
                                <g:message code="openart.technical.maxFileSize" default="Maximum file size" /> ${grailsApplication.config.openart.maxUploadSize.text}MB
                                <g:if test="${instance?.attachmentFile}"><br /><g:message code="openart.technical.uploadWillReplace" default="Uploading a new file will replace the old one" /></g:if>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><input type="submit" value="${g.message(code: 'openart.uo.submit', default: 'Create submission')}" /></span>
        </div>
    </g:uploadForm>
</body>
