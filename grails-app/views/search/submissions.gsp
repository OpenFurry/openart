<html><!-- TODO i18n -->
    <head>
        <title><g:message code="openart.search" default="Search" /> - <g:message code="openart.search.submissions" default="Submissions" /> - ${searchTerm}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="breadcrumbs">
            <g:link controller="search"><g:message code="openart.search" default="Search" /></g:link> &raquo;
            <g:message code="openart.search.submissions" default="Submissions" />
        </div>
        <g:render template="/list" />
    </body>
</html>
