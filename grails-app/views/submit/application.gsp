<head>
    <meta name="layout" content="main" />
    <title><g:message code="openfurry.applicationUO.view.create" default="New application submission" /></title>
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
                        <th colspan="2"><g:message code="openfurry.applicationUO.sectionTitle" default="Application submission details" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.applicationUO.file" default="Application screenshot file" /></th>
                        <td class="value">
                            <g:if test="${instance?.screenShot}"><g:message code="openfurry.technical.currentAttachment" default="Current attachment:" /> ${instance.screenShot}</g:if>
                            <input type="file" name="fileUpload" />
                            <div class="message"><g:message code="openfurry.technical.allowedTypes" default="Allowed file types" /> ${grailsApplication.config.openfurry.fileTypes.application}<br />
                                <g:message code="openfurry.technical.maxFileSize" default="Maximum file size" /> ${grailsApplication.config.openfurry.maxUploadSize.application}MB
                                <g:if test="${instance?.screenShot}"><br /><g:message code="openfurry.technical.uploadWillReplace" default="Uploading a new file will replace the old one" /></g:if>
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
