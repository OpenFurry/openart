<head>
	<meta name="layout" content="main" />
	<title>Create Person</title>
</head>

<body>

	<div class="nav">
		<span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
		<span class="menuButton"><g:link class="list" action="list">Person List</g:link></span>
	</div>

	<div class="body">
		<h1>Create Person</h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${user}">
		<div class="errors">
			<g:renderErrors bean="${user}" as="list" />
		</div>
		</g:hasErrors>
		<g:form action="save">
			<div class="dialog">
				<table>
				<tbody>

					<tr class="prop">
						<td valign="top" class="name"><label for="username">Login Name:</label></td>
						<td valign="top" class="value ${hasErrors(bean:user,field:'username','errors')}">
							<input type="text" id="username" name="username" value="${user.username?.encodeAsHTML()}"/>
						</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name"><label for="userRealName">Full Name:</label></td>
						<td valign="top" class="value ${hasErrors(bean:user,field:'userRealName','errors')}">
							<input type="text" id="userRealName" name="userRealName" value="${user.userRealName?.encodeAsHTML()}"/>
						</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name"><label for="passwd">Password:</label></td>
						<td valign="top" class="value ${hasErrors(bean:user,field:'passwd','errors')}">
							<input type="password" id="passwd" name="passwd" value="${user.passwd?.encodeAsHTML()}"/>
						</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name"><label for="enabled">Enabled:</label></td>
						<td valign="top" class="value ${hasErrors(bean:user,field:'enabled','errors')}">
							<g:checkBox name="enabled" value="${user.enabled}" ></g:checkBox>
						</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name"><label for="description">Description:</label></td>
						<td valign="top" class="value ${hasErrors(bean:user,field:'description','errors')}">
							<input type="text" id="description" name="description" value="${user.description?.encodeAsHTML()}"/>
						</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name"><label for="email">Email:</label></td>
						<td valign="top" class="value ${hasErrors(bean:user,field:'email','errors')}">
							<input type="text" id="email" name="email" value="${user.email?.encodeAsHTML()}"/>
						</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name"><label for="emailShow">Show Email:</label></td>
						<td valign="top" class="value ${hasErrors(bean:user,field:'emailShow','errors')}">
							<g:checkBox name="emailShow" value="${user.emailShow}"/>
						</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name" align="left">Assign Roles:</td>
					</tr>

					<g:each in="${authorityList}">
					<tr>
						<td valign="top" class="name" align="left">${it.authority.encodeAsHTML()}</td>
						<td align="left"><g:checkBox name="${it.authority}"/></td>
					</tr>
					</g:each>

				</tbody>
				</table>
			</div>

			<div class="buttons">
				<span class="button"><input class="save" type="submit" value="Create" /></span>
			</div>

		</g:form>

	</div>
</body>
