<html>
    <head>
        <title><g:message code="openfurry.collection.ordered.view.create" default="New ordered collection" /></title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:hasErrors bean="${instance}">
        <div class="errors">
            <g:renderErrors bean="${instance}" as="list" />
        </div>
        </g:hasErrors>
        <g:uploadForm action="saveOrderedCollection" method="post">
            <g:render template="uoform" />
            <div class="block">
                <table>
                    <thead>
                        <tr>
                            <th colspan="2"><g:message code="openfurry.collection.ordered.sectionTitle" default="Ordered collection details" /></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="prop">
                            <th class="name"><g:message code="openfurry.collection.description" default="Collection description" /></th>
                            <td class="value"><g:textArea name="description" rows="10" cols="75" /></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <span class="button"><input type="submit" value="Create submission" /></span>
            </div>
        </g:uploadForm>
    </body>
</html>
