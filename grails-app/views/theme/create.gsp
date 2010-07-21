<html>
    <head>
        <title><g:message code="openfurry.theme.view.create" default="New OpenFurry theme" /></title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <g:hasErrors bean="${instance}">
        <div class="errors">
            <g:renderErrors bean="${instance}" as="list" />
        </div>
        </g:hasErrors>
        <g:form action="save" method="post">
            <table>
                <thead>
                    <tr>
                        <th class="shadow" colspan="2"><g:message code="openfurry.theme.section.body" default="Body" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.theme.types.bgcolor" default="Background color" /></th>
                        <td class="value"><g:textField name="theme.body.bgcolor" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.theme.types.fgcolor" default="Font color" /></th>
                        <td class="value"><g:textField name="theme.body.fgcolor" /></td>
                    </tr>
                    <tr>
                        <th class="name"><g:message code="openfurry.theme.types.font" default="Font" /></th>
                        <td class="value">
                            <select name="theme.body.font">
                                <option value="serif" style="font-family: serif">Serif</option>
                                <option value="sans-serif" style="font-family: sans-erif">Sans-serif</option>
                                <option value="cursive" style="font-family: cursive">Cursive</option>
                                <option value="fantasy" style="font-family: fantasy">Fantasy</option>
                                <option value="monospace" style="font-family: monospace">Monospace</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th class="name"><g:message code="openfurry.theme.types.fontSize" default="Font size" /></th>
                        <td class="value"><g:textField name="theme.body.fontSize" /></td>
                    </tr>
                </tbody>
            </table>
            <table>
                <thead>
                    <tr>
                        <th class="shadow" colspan="2"><g:message code="openfurry.theme.section.header" default="Header" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.theme.types.bgcolor" default="Background color" /></th>
                        <td class="value"><g:textField name="theme.header.bgcolor" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.theme.types.fgcolor" default="Font color" /></th>
                        <td class="value"><g:textField name="theme.header.fgcolor" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.theme.types.border" default="Border" /></th>
                        <td class="value">
                            <select name="theme.header.borderStyle">
                                <option value="none">None</option>
                                <option value="solid">Solid</option>
                                <option value="dotted">Dotted</option>
                                <option value="dashed">Dashed</option>
                            </select>
                            <g:textField name="theme.header.borderColor" />
                        </td>
                    </tr>
                </tbody>
            </table>
            <table>
                <thead>
                    <tr>
                        <th class="shadow" colspan="2"><g:message code="openfurry.theme.section.navigation" default="Navigation" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.theme.types.fgcolor" default="Font color" /></th>
                        <td class="value"><g:textField name="theme.navigation.fgcolor" /></td>
                    </tr>
                </tbody>
            </table>
            <table>
                <thead>
                    <tr>
                        <th class="shadow" colspan="2"><g:message code="openfurry.theme.section.content" default="Content" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.theme.types.bgcolor" default="Background color" /></th>
                        <td class="value"><g:textField name="theme.content.bgcolor" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.theme.types.fgcolor" default="Font color" /></th>
                        <td class="value"><g:textField name="theme.content.fgcolor" /></td>
                    </tr>
                </tbody>
            </table>
            <table>
                <thead>
                    <tr>
                        <th class="shadow" colspan="2"><g:message code="openfurry.theme.section.blocks" default="Blocks" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.theme.types.bgcolor" default="Background color" /></th>
                        <td class="value"><g:textField name="theme.block.bgcolor" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.theme.types.fgcolor" default="Font color" /></th>
                        <td class="value"><g:textField name="theme.block.fgcolor" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.theme.types.border" default="Border" /></th>
                        <td class="value">
                            <select name="theme.block.borderStyle">
                                <option value="none">None</option>
                                <option value="solid">Solid</option>
                                <option value="dotted">Dotted</option>
                                <option value="dashed">Dashed</option>
                            </select>
                            <g:textField name="theme.block.borderColor" />
                        </td>
                    </tr>
                </tbody>
            </table>
            <table>
                <thead>
                    <tr>
                        <th class="shadow" colspan="2"><g:message code="openfurry.theme.section.htags" default="Header tags (h2, h3, ...)" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.theme.types.bgcolor" default="Background color" /></th>
                        <td class="value"><g:textField name="theme.headers.bgcolor" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.theme.types.fgcolor" default="Font color" /></th>
                        <td class="value"><g:textField name="theme.headers.fgcolor" /></td>
                    </tr>
                    <tr>
                        <th class="name"><g:message code="openfurry.theme.types.fontSize" default="Font size" /></th>
                        <td class="value"><g:textField name="theme.headers.fontSize" /></td>
                    </tr>
                </tbody>
            </table>
            <table>
                <thead>
                    <tr>
                        <th class="shadow" colspan="2"><g:message code="openfurry.theme.section.forms" default="Forms" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.theme.types.bgcolor" default="Background color" /> - <g:message code="openfurry.theme.label" default="Label" /></th>
                        <td class="value"><g:textField name="theme.th.bgcolor" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.theme.types.fgcolor" default="Font color" /> - <g:message code="openfurry.theme.label" default="Label" /></th>
                        <td class="value"><g:textField name="theme.th.fgcolor" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.theme.types.bgcolor" default="Background color" /> - <g:message code="openfurry.theme.field" default="Field" /></th>
                        <td class="value"><g:textField name="theme.td.bgcolor" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.theme.types.fgcolor" default="Font color" /> - <g:message code="openfurry.theme.field" default="Field" /></th>
                        <td class="value"><g:textField name="theme.td.fgcolor" /></td>
                    </tr>
                    <tr class="prop">
                        <th class="name"><g:message code="openfurry.theme.types.border" default="Border" /></th>
                        <td class="value">
                            <select name="theme.th.borderStyle">
                                <option value="none">None</option>
                                <option value="solid">Solid</option>
                                <option value="dotted">Dotted</option>
                                <option value="dashed">Dashed</option>
                            </select>
                            <g:textField name="theme.th.borderColor" />
                        </td>
                    </tr>
                </tbody>
            </table>
            <div class="buttons">
                <span class="button"><input type="submit" value="Create theme" /></span>
            </div>
        </g:form>
    </body>
</html>
