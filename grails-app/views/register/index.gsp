<head>
	<meta name="layout" content="main" />
	<title>User Registration</title>
</head>

<body>

	<div class="body">
		<h1>User Registration</h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${person}">
		<div class="errors">
			<g:renderErrors bean="${person}" as="list" />
		</div>
		</g:hasErrors>

		<g:form action="save">
		<div class="dialog">
		<table>
		<tbody>

			<tr class='prop'>
				<td valign='top' class='name'><label for='username'><g:message code="openfurry.user.username" default="Username" /></label></td>
				<td valign='top' class='value ${hasErrors(bean:person,field:'username','errors')}'>
					<input type="text" name='username' value="${person?.username?.encodeAsHTML()}"/>
				</td>
			</tr>

			<tr class='prop'>
				<td valign='top' class='name'><label for='userRealName'><g:message code="openfurry.user.userRealName" default="Display name" /></label></td>
				<td valign='top' class='value ${hasErrors(bean:person,field:'userRealName','errors')}'>
					<input type="text" name='userRealName' value="${person?.userRealName?.encodeAsHTML()}"/>
				</td>
			</tr>

			<tr class='prop'>
				<td valign='top' class='name'><label for='passwd'><g:message code="openfurry.user.password" default="Password" /></label></td>
				<td valign='top' class='value ${hasErrors(bean:person,field:'passwd','errors')}'>
					<input type="password" name='passwd' value="${person?.passwd?.encodeAsHTML()}"/>
				</td>
			</tr>

			<tr class='prop'>
				<td valign='top' class='name'><label for='repasswd'><g:message code="openfurry.user.confirmPassword" default="Confirm password" /></label></td>
				<td valign='top' class='value ${hasErrors(bean:person,field:'passwd','errors')}'>
					<input type="password" name='repasswd' value="${person?.passwd?.encodeAsHTML()}"/>
				</td>
			</tr>

			<tr class='prop'>
				<td valign='top' class='name'><label for='email'>Email:</label></td>
				<td valign='top' class='value ${hasErrors(bean:person,field:'email','errors')}'>
					<input type="text" name='email' value="${person?.email?.encodeAsHTML()}"/>
				</td>
			</tr>

            <tr class="prop" style="display: none">
                <th class="name"><label for="hp"><g:message code="openfurry.user.honeypot" default="Do not enter anything in this field; if you do, your registration will be rejected" />
                <td class="value"><input type="text" name="hp" /></td>
            </tr>

            <input type="hidden" name="tstart" value="${tstart}" />

		</tbody>
		</table>
		</div>

		<div class="buttons">
			<span class="formButton">
				<input class='save' type="submit" value="Create"></input>
			</span>
		</div>

		</g:form>
	</div>
</body>
