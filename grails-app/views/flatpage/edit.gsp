<%@ page import="com.mjs_svc.openart.Flatpage" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'flatpage.label', default: 'Flatpage')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${flatpageInstance}">
            <div class="errors">
                <g:renderErrors bean="${flatpageInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${flatpageInstance?.id}" />
                <g:hiddenField name="version" value="${flatpageInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="slug"><g:message code="flatpage.slug.label" default="Slug" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: flatpageInstance, field: 'slug', 'errors')}">
                                    <g:textField name="slug" value="${flatpageInstance?.slug}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="title"><g:message code="flatpage.title.label" default="Title" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: flatpageInstance, field: 'title', 'errors')}">
                                    <g:textField name="title" value="${flatpageInstance?.title}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="content"><g:message code="flatpage.content.label" default="Content" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: flatpageInstance, field: 'content', 'errors')}">
                                    <g:textField name="content" value="${flatpageInstance?.content}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="layout"><g:message code="flatpage.layout.label" default="Layout" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: flatpageInstance, field: 'layout', 'errors')}">
                                    <g:textField name="layout" value="${flatpageInstance?.layout}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
