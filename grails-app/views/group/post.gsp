<html><!-- TODO i18n -->
    <head>
        <title>POST</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:form action="savePost" method="post">
            <input type="hidden" name="groupId" value="${groupId}" />
            <table>
                <thead>
                    <tr>
                        <th colspan="2">POST DETAILS</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name">POST TITLE</th>
                        <td class="value"><g:textField name="title" value="${instance?.title}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name">POST BODY
                            <div class="message">
                                <p><g:link controller="flatpage" action="show" id="markdown"><g:message code="openart.messages.markdownOkay" default="Markdown allowed" /></g:link></p>
                                <p><g:link controller="flatpage" action="show" id="linking"><g:message code="openart.messages.linkingOkay" default="Linking allowed" /></g:link></p>
                            </div>
                        </th>
                        <td class="value"><g:textArea name="post" value="${instance?.post}" rows="10" cols="75" /></td>
                    </tr>
                </tbody>
            </table>
            <div class="buttons"><input type="submit" /></div>
        </g:form>
    </body>
</html>
