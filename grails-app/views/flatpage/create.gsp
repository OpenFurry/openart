<%@ page import="com.mjs_svc.openart.Flatpage" %>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'flatpage.label', default: 'Flatpage')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <g:hasErrors bean="${flatpageInstance}">
            <div class="errors">
                <g:renderErrors bean="${flatpageInstance}" as="list" />
            </div>
        </g:hasErrors>
        <g:form action="save" method="post" >
            <table>
                <tbody>
                
                    <tr class="prop">
                        <th valign="top" class="name">
                            <label for="slug"><g:message code="flatpage.slug.label" default="Slug" /></label>
                        </th>
                        <td valign="top" class="value ${hasErrors(bean: flatpageInstance, field: 'slug', 'errors')}">
                            <g:textField name="slug" value="${flatpageInstance?.slug}" />
                        </td>
                    </tr>
                
                    <tr class="prop">
                        <th valign="top" class="name">
                            <label for="title"><g:message code="flatpage.title.label" default="Title" /></label>
                        </th>
                        <td valign="top" class="value ${hasErrors(bean: flatpageInstance, field: 'title', 'errors')}">
                            <g:textField name="title" value="${flatpageInstance?.title}" />
                        </td>
                    </tr>
                
                    <tr class="prop">
                        <th valign="top" class="name">
                            <label for="content"><g:message code="flatpage.content.label" default="Content" /></label>
                        </th>
                        <td valign="top" class="value ${hasErrors(bean: flatpageInstance, field: 'content', 'errors')}">
                            <g:textArea id="fpcontent" name="content" value="${flatpageInstance?.content}" rows="10" cols="75"/>
                        </td>
                    </tr>
                
                    <tr class="prop">
                        <th valign="top" class="name">
                            <label for="layout"><g:message code="flatpage.layout.label" default="Layout" /></label>
                        </th>
                        <td valign="top" class="value ${hasErrors(bean: flatpageInstance, field: 'layout', 'errors')}">
                            <g:textField name="layout" value="${flatpageInstance?.layout}" />
                        </td>
                    </tr>
                
                </tbody>
            </table>
            <div class="buttons">
                <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
            </div>
        </g:form>
    </body>
</html>
