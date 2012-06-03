<head>
	<meta name="layout" content="main" />
	<title>Edit Profile</title>
</head>

<body>

	<div class="nav">
		<span class="menuButton"><a class='home' href="${resource(dir:'')}">Home</a></span>
	</div>

	<div class="body">
		<h1>Edit Profile</h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${user}">
		<div class="errors">
			<g:renderErrors bean="${user}" as="list" />
		</div>
		</g:hasErrors>

		<g:form>
			<input type="hidden" name="id" value="${user.id}" />
			<input type="hidden" name="version" value="${user.version}" />
			<div class="dialog">
			<table>
				<tbody>
				<tr class='prop'>
					<td valign='top' class='name'><label for='username'>Login Name:</label></td>
					<td valign='top' class='value ${hasErrors(bean:user,field:'username','errors')}'>
						<input type="hidden" name='username' value="${user.username?.encodeAsHTML()}"/>
						<div style="margin:3px">${user.username?.encodeAsHTML()}</div>
					</td>
				</tr>

				<tr class='prop'>
					<td valign='top' class='name'><label for='userRealName'>Full Name:</label></td>
					<td valign='top' class='value ${hasErrors(bean:user,field:'userRealName','errors')}'>
						<input type="text" name='userRealName' value="${user.userRealName?.encodeAsHTML()}"/>
					</td>
				</tr>

				<tr class='prop'>
					<td valign='top' class='name'><label for='passwd'>Password:</label></td>
					<td valign='top' class='value ${hasErrors(bean:user,field:'passwd','errors')}'>
						<input type="password" name='passwd' value=""/>
					</td>
				</tr>

				<tr class='prop'>
					<td valign='top' class='name'><label for='enabled'>Confirm Password:</label></td>
					<td valign='top' class='value ${hasErrors(bean:user,field:'passwd','errors')}'>
						<input type="password" name='repasswd' />
					</td>
				</tr>

				<tr class='prop'>
					<td valign='top' class='name'><label for='email'>Email:</label></td>
					<td valign='top' class='value ${hasErrors(bean:user,field:'email','errors')}'>
						<input type="text" name='email' value="${user.email?.encodeAsHTML()}"/>
					</td>
				</tr>

				<tr class='prop'>
					<td valign='top' class='name'><label for='emailShow'>Show Email:</label></td>
					<td valign='top' class='value ${hasErrors(bean:user,field:'emailShow','errors')}'>
						<g:checkBox name='emailShow' value="${user.emailShow}" ></g:checkBox>
					</td>
				</tr>

				</tbody>
			</table>
			</div>

			<div class="buttons">
				<span class="button"><g:actionSubmit class='save' value="Update" /></span>
			</div>

		</g:form>

	</div>
</body>
