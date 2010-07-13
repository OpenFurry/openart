<head>
    <meta name="layout" content="main" />
    <title><g:message code="openfurry.imageUO.view.create" default="New image submission" /></title>
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
                        <th colspan="2"><g:message code="openfurry.imageUO.sectionTitle" default="Image submission details" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.imageUO.file" default="Image file" /></th>
                        <td class="value">
                            <g:if test="${instance?.fullFile}"><g:message code="openfurry.technical.currentAttachment" default="Current attachment:" /> ${instance.fullFile}</g:if>
                            <input type="file" name="imageFile" />
                            <div class="message"><g:message code="openfurry.technical.allowedTypes" default="Allowed file types" /> ${grailsApplication.config.openfurry.fileTypes.image}<br />
                                <g:message code="openfurry.technical.maxFileSize" default="Maximum file size" /> ${grailsApplication.config.openfurry.maxUploadSize.image}MB
                                <p><g:message code="openfurry.imageUO.file.resizeNote" default="Three images will be stored: a thumbnail, a sized image, and the full uploaded image" /></p>
                                <g:if test="${instance?.fullFile}"><br /><g:message code="openfurry.technical.uploadWillReplace" default="Uploading a new file will replace the old one" /></g:if>
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
