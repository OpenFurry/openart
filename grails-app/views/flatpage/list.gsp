
<%@ page import="openfurry.Flatpage" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'flatpage.label', default: 'Flatpage')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'flatpage.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="slug" title="${message(code: 'flatpage.slug.label', default: 'Slug')}" />
                        
                            <g:sortableColumn property="title" title="${message(code: 'flatpage.title.label', default: 'Title')}" />
                        
                            <g:sortableColumn property="content" title="${message(code: 'flatpage.content.label', default: 'Content')}" />
                        
                            <g:sortableColumn property="layout" title="${message(code: 'flatpage.layout.label', default: 'Layout')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${flatpageInstanceList}" status="i" var="flatpageInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${flatpageInstance.id}">${fieldValue(bean: flatpageInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: flatpageInstance, field: "slug")}</td>
                        
                            <td>${fieldValue(bean: flatpageInstance, field: "title")}</td>
                        
                            <td>${fieldValue(bean: flatpageInstance, field: "content")}</td>
                        
                            <td>${fieldValue(bean: flatpageInstance, field: "layout")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${flatpageInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
