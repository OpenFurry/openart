<head>
	<meta name="layout" content="main" />
	<title>Show Person</title>
</head>

<body>
    <h1>Show Person</h1>
    <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
        <tbody>

            <tr class="prop">
                <td valign="top" class="name">ID:</td>
                <td valign="top" class="value">${person.id}</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name">Login Name:</td>
                <td valign="top" class="value">${person.username?.encodeAsHTML()}</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name">Full Name:</td>
                <td valign="top" class="value">${person.userRealName?.encodeAsHTML()}</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name">Enabled:</td>
                <td valign="top" class="value">${person.enabled}</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name">Profile:</td>
                <td valign="top" class="value">${person.profile?.encodeAsHTML()}</td>
            </tr>

            <tr class="prop">
                <td class="name">Species:</td>
                <td class="value"><of:speciesString species="${person.species}" /></td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name">Roles:</td>
                <td valign="top" class="value">
                    <ul>
                    <g:each in="${roleNames}" var='name'>
                        <li>${name}</li>
                    </g:each>
                    </ul>
                </td>
            </tr>

        </tbody>
        </table>
    </div>
</body>
