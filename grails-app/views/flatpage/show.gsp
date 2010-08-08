<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="${flatpageInstance.layout}" />
        <title>${flatpageInstance.title}</title>
    </head>
    <body>
        <table>
            <tbody>
                <tr class="prop">
                    <th class="name">${flatpageInstance.title}</th>
                    <td class="value"><of:linking><markdown:renderHtml>${flatpageInstance.content}</markdown:renderHtml></of:linking></td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
