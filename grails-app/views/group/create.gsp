<html>
    <head>
        <title>CREATE GROUP</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:form action="save" method="post">
            <table>
                <thead>
                    <tr>
                        <th colspan="2">GROUP DETAILS</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name">SLUG</th>
                        <td class="value"><g:textField name="slug" value="${instance?.slug}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name">TITLE</th>
                        <td class="value"><g:textField name="title" value="${instance?.title}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name">DESCRIPTION</th>
                        <td class="value"><g:textArea name="description" value="${instance?.description}" rows="10" cols="75" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name">EXCLUSIVE</th>
                        <td class="value"><g:checkBox name="exclusive" /></td>
                    </tr>
                </tbody>
            </table>
            <div class="buttons"><input type="submit" />
        </g:form>
    </body>
</html>
