<head>
	<meta name="layout" content="main" />
	<title>${person.userRealName.encodeAsHTML()} (~${person.username.encodeAsHTML()})</title>
</head>

<body>
    <div class="block">
        <div class="shadow blocktitle">
            <g:message code="openfurry.user.profile" default="Profile" />
        </div>
        ${person.profile.encodeAsOpenFurry()}<!-- TODO: markdown -->
    </div>
    <div class="block" style="width: 30%">
        <table>
            <thead>
                <tr>
                    <th colspan="2" class="shadow"><g:message code="openfurry.user.details" default="User details" /></th>
                </tr>
            </thead>
            <tbody>
                <tr class="prop">
                    <th class="name"></th>
                    <td class="value"></td>
                </tr>
            </tbody>
        </table>
    </div>
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
