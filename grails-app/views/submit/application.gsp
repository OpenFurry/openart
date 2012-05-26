<head>
    <meta name="layout" content="main" />
    <title><g:message code="openart.applicationUO.view.create" default="New application submission" /></title>
</head>

<body>
    <g:hasErrors bean="${instance}">
    <div class="errors">
        <g:renderErrors bean="${instance}" as="list" />
    </div>
    </g:hasErrors>
    <g:uploadForm action="saveApplication" method="post">
        <div class="dialog">
            <g:render template="uoform" />
            <table>
                <thead>
                    <tr>
                        <th colspan="2"><g:message code="openart.applicationUO.sectionTitle" default="Application submission details" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name"><g:message code="openart.applicationUO.file" default="Application screenshot file" /></th>
                        <td class="value">
                            <g:if test="${instance?.screenShot}"><g:message code="openart.technical.currentAttachment" default="Current attachment:" /> ${instance.screenShot}</g:if>
                            <input type="file" name="fileUpload" />
                            <div class="message"><g:message code="openart.technical.allowedTypes" default="Allowed file types" /> ${grailsApplication.config.openart.fileTypes.application}<br />
                                <g:message code="openart.technical.maxFileSize" default="Maximum file size" /> ${grailsApplication.config.openart.maxUploadSize.application}MB
                                <g:if test="${instance?.screenShot}"><br /><g:message code="openart.technical.uploadWillReplace" default="Uploading a new file will replace the old one" /></g:if>
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
