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
                        <th class="name">CATEGORY</th>
                        <td class="value">
                            <select name="category">
                                <option value="0"><g:message code="openfurry.group.category.${grailsApplication.config.openfurry.group.category.repr[0]}" default="${grailsApplication.config.openfurry.group.category.repr[0]}" /></option>
                                <option value="1"><g:message code="openfurry.group.category.${grailsApplication.config.openfurry.group.category.repr[1]}" default="${grailsApplication.config.openfurry.group.category.repr[1]}" /></option>
                                <option value="2"><g:message code="openfurry.group.category.${grailsApplication.config.openfurry.group.category.repr[2]}" default="${grailsApplication.config.openfurry.group.category.repr[2]}" /></option>
                                <option value="3"><g:message code="openfurry.group.category.${grailsApplication.config.openfurry.group.category.repr[3]}" default="${grailsApplication.config.openfurry.group.category.repr[3]}" /></option>
                                <option value="4"><g:message code="openfurry.group.category.${grailsApplication.config.openfurry.group.category.repr[4]}" default="${grailsApplication.config.openfurry.group.category.repr[4]}" /></option>
                                <option value="5"><g:message code="openfurry.group.category.${grailsApplication.config.openfurry.group.category.repr[5]}" default="${grailsApplication.config.openfurry.group.category.repr[5]}" /></option>
                            </select>
                        </td>
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
