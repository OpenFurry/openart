<html><!-- TODO i18n -->
    <head>
        <title><g:message code="openfurry.user.properties" default="Properties" /></title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <th colspan="2"><g:message code="openfurry.user.properties.yours" default="Your properties" /></th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${user.props}">
                    <of:userProperty key="${it.key}" value="${it.value}" />
                </g:each>
            </tbody>
        </table>
        <g:form action="addProperty">
            <table>
                <thead>
                    <tr>
                        <th colspan="2"><g:message code="openfurry.user.properties.add" default="Add property" /></th>
                    </tr>
                </thead>
                <tbody>
                    <of:propertyForm />
                </tbody>
            </table>
            <div class="buttons"><input type="submit" /></div>
        </g:form>
    </body>
</html>
