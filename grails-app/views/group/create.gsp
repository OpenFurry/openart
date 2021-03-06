<html>
    <head>
        <title><g:message code="openart.group.views.create" default="Create a new group" /></title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:form action="save" method="post">
            <table>
                <thead>
                    <tr>
                        <th colspan="2"><g:message code="openart.group.details" default="Group details" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name"><g:message code="openart.group.slug" default="slug" /></th>
                        <td class="value">
                            <g:textField name="slug" value="${instance?.slug}" />
                            <div class="message"><g:message code="openart.group.slug.details" default="A slug is a unique identifier for the group used in that group's URL" /></div>
                        </td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openart.group.title" default="Title" /></th>
                        <td class="value"><g:textField name="title" value="${instance?.title}" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openart.group.description" default="Description" />
                            <div class="message">
                                <p><g:link controller="flatpage" action="show" id="markdown"><g:message code="openart.messages.markdownOkay" default="Markdown allowed" /></g:link></p>
                                <p><g:link controller="flatpage" action="show" id="linking"><g:message code="openart.messages.linkingOkay" default="Linking allowed" /></g:link></p>
                            </div>
                        </th>
                        <td class="value"><g:textArea name="description" value="${instance?.description}" rows="10" cols="75" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openart.group.category" default="Category" /></th>
                        <td class="value">
                            <select name="category">
                                <option value="0"><g:message code="openart.group.category.${grailsApplication.config.openart.group.category.repr[0]}" default="${grailsApplication.config.openart.group.category.repr[0]}" /></option>
                                <option value="1"><g:message code="openart.group.category.${grailsApplication.config.openart.group.category.repr[1]}" default="${grailsApplication.config.openart.group.category.repr[1]}" /></option>
                                <option value="2"><g:message code="openart.group.category.${grailsApplication.config.openart.group.category.repr[2]}" default="${grailsApplication.config.openart.group.category.repr[2]}" /></option>
                                <option value="3"><g:message code="openart.group.category.${grailsApplication.config.openart.group.category.repr[3]}" default="${grailsApplication.config.openart.group.category.repr[3]}" /></option>
                                <option value="4"><g:message code="openart.group.category.${grailsApplication.config.openart.group.category.repr[4]}" default="${grailsApplication.config.openart.group.category.repr[4]}" /></option>
                                <option value="5"><g:message code="openart.group.category.${grailsApplication.config.openart.group.category.repr[5]}" default="${grailsApplication.config.openart.group.category.repr[5]}" /></option>
                            </select>
                        </td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openart.group.exclusive" default="Exclusive" /></th>
                        <td class="value">
                            <g:checkBox name="exclusive" />
                            <div class="message"><g:message code="openart.group.exclusive.details" default="Exclusive groups are groups whose messages and events are private to that group&apos;s members; however, the group&apos;s title and description are still viewable to all" /></div>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div class="buttons"><input type="submit" />
        </g:form>
    </body>
</html>
