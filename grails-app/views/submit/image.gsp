<head>
    <meta name="layout" content="main" />
    <title><g:message code="openart.imageUO.view.create" default="New image submission" /></title>
</head>

<body>
    <g:hasErrors bean="${instance}">
    <div class="errors">
        <g:renderErrors bean="${instance}" as="list" />
    </div>
    </g:hasErrors>
    <g:uploadForm action="saveImage" method="post">
        <div class="dialog">
            <g:render template="uoform" />
            <table>
                <thead>
                    <tr>
                        <th colspan="2"><g:message code="openart.imageUO.sectionTitle" default="Image submission details" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name"><g:message code="openart.imageUO.file" default="Image file" /></th>
                        <td class="value">
                            <g:if test="${instance?.fullFile}"><g:message code="openart.technical.currentAttachment" default="Current attachment:" /> ${instance.fullFile}</g:if>
                            <input type="file" name="imageFile" />
                            <div class="message"><g:message code="openart.technical.allowedTypes" default="Allowed file types" /> ${grailsApplication.config.openart.fileTypes.image}<br />
                                <g:message code="openart.technical.maxFileSize" default="Maximum file size" /> ${grailsApplication.config.openart.maxUploadSize.image}MB
                                <p><g:message code="openart.imageUO.file.resizeNote" default="Three images will be stored: a thumbnail, a sized image, and the full uploaded image" /></p>
                                <g:if test="${instance?.fullFile}"><br /><g:message code="openart.technical.uploadWillReplace" default="Uploading a new file will replace the old one" /></g:if>
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
