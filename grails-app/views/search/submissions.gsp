<html><!-- TODO i18n -->
    <head>
        <title><g:message code="openfurry.search" default="Search" /> - <g:message code="openfurry.search.submissions" default="Submissions" /> - ${searchTerm}</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="breadcrumbs">
            <g:link controller="search"><g:message code="openfurry.search" default="Search" /></g:link> &raquo;
            <g:message code="openfurry.search.submissions" default="Submissions" />
        </div>
        <g:render template="/list" />
    </body>
</html>
