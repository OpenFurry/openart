<head>
    <meta name="layout" content="main" />
    <title><g:message code="openfurry.videoUO.view.create" default="New video submission" /></title>
</head>

<body>
    <g:hasErrors bean="${instance}">
    <div class="errors">
        <g:renderErrors bean="${instance}" as="list" />
    </div>
    </g:hasErrors>
    <g:uploadForm action="saveVideo" method="post">
        <div class="dialog">
            <g:render template="uoform" />
            <table>
                <thead>
                    <tr>
                        <th colspan="2"><g:message code="openfurry.videoUO.sectionTitle" default="Video submission details" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.videoUO.file" default="Video file" /></th>
                        <td class="value">
                            <g:if test="${instance?.file}"><g:message code="openfurry.technical.currentAttachment" default="Current attachment:"/> ${instance.file</g:if>
                            <input type="file" name="videFile" />
                            <div class="message"><g:message code="openfurry.technical.allowedTypes" default="Allowed file types" /> ${grailsApplication.config.openfurry.fileTypes.video}<br />
                                <g:message code="openfurry.technical.maxFileSize" default="Maximum file size" /> ${grailsApplication.config.openfurry.maxUploadSize.video}MB
                                <g:if test="${instance?.file}"><br /><g:message code="openfurry.technical.uploadWillReplace" default="Uploading a new file will replace the old one" /></g:if>
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
