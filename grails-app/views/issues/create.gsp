<html>
    <head>
        <title><!-- TODO --></title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:form action="save" method="post">
        <table>
            <thead>
                <tr>
                    <th colspan="2"><!-- TODO create issue --></th>
                </tr>
            </thead>
            <tbody>
                <tr class="prop">
                    <td class="name"><g:message code="openfurry.issue.title" default="Issue title" /></td>
                    <td class="value"><g:textField name="title" /></td>
                </tr>
                <tr class="prop">
                    <td class="name"><g:message code="openfurry,issue.description" default="Description" /></td>
                    <td class="value"><g:textArea name="description" rows="10" cols="75" /></td>
                </tr>
            </tbody>
        </table>
        <div class="shadow buttons"><input type="submit" /></div>
        </g:form>
    </body>
</html>
