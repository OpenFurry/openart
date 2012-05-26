<head>
    <meta name="layout" content="main" />
    <title><g:message code="openart.flashUO.view.create" default="New flash submission" /></title>
</head>

<body>
    <g:hasErrors bean="${instance}">
    <div class="errors">
        <g:renderErrors bean="${instance}" as="list" />
    </div>
    </g:hasErrors>
    <g:uploadForm action="saveFlash" method="post">
        <div class="dialog">
            <g:render template="uoform" />
            <table>
                <thead>
                    <tr>
                        <th colspan="2"><g:message code="openart.flashUO.sectionTitle" default="Flash submission details" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name"><g:message code="openart.flashUO.file" default="Flash file" /></th>
                        <td class="value">
                            <g:if test="${instance?.file}"><g:message code="openart.technical.currentAttachment" default="Current attachment:" /> ${instance.file}</g:if>
                            <input type="file" name="flashFile" />
                            <div class="message"><g:message code="openart.technical.allowedTypes" default="Allowed file types" /> ${grailsApplication.config.openart.fileTypes.flash}<br />
                                <g:message code="openart.technical.maxFileSize" default="Maximum file size" /> ${grailsApplication.config.openart.maxUploadSize.flash}MB
                                <g:if test="${instance?.file}"><br /><g:message code="openart.technical.uploadWillReplace"  default="Uploading a new file will replace the old one" /></g:if>
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
