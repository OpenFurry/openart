<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="openart.audioUO.view.create" default="New audio submission" /></title>
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
                            <th colspan="2"><g:message code="openart.audioUO.sectionTitle" default="Audio submission details" /></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="prop">
                            <th class="name"><g:message code="openart.audioUO.file" defualt="Audio file" /></th>
                            <td class="value">
                                <g:if test="${instance?.file}">Current audio file: ${instance.file}<input type="hidden" name="file" value="${instance.file}" /></g:if>
                                <input type="file" name="audioFile" /> <br />
                                <div class="message"><g:message code="openart.technical.allowedTypes" defualt="Allowed file types" /> ${grailsApplication.config.openart.fileTypes.audio}<br />
                                    <g:message code="openart.technical.maxFileSize" default="Maximum file size" /> ${grailsApplication.config.openart.maxUploadSize.audio}MB
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
</html>
