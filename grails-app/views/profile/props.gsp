<html><!-- TODO i18n -->
    <head>
        <title>PROPERTIES</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <th colspan="2">PROPERTIES</th>
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
                        <th colspan="2">ADD PROPERTY</th>
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
