package us.jnsq.openart

import us.jnsq.openart.security.*

/**
 * User controller.
 */
class UserController {

	def authenticateService

    def listService

	// the delete, save and update actions only accept POST requests
	static Map allowedMethods = [delete: 'POST', save: 'POST', update: 'POST']

	def index = {
		redirect action: list, params: params
	}

	def list = {
		if (!params.max) {
			params.max = 10
		}
		[userList: User.list(params)]
	}

    def friends = {
        def user = User.findByUsername(params.id)
        if (!user) {
            response.sendError(404)
            return
        }

        [user: user]
    }

	def show = {
		def user = params.username ? User.findByUsername(params.username) : User.get(params.id)
		if (!user) {
            response.sendError(404, "Not Found") // i18n
			return
		}
		List roleNames = []
		for (role in user.authorities) {
			roleNames << role.authority.split("_")[1].toLowerCase()
		}
		roleNames.sort { n1, n2 ->
			n1 <=> n2
		}

        def watched = false
        if (authenticateService.isLoggedIn()) {
            def p = User.findByUsername(authenticateService.principal().username)
            if (user in p.watches) {
                watched = true
            }
        }

        def criteria = {
            eq("owner", user)
        }
        def submissions = listService.listUOsForRating(criteria, params)
        params.totalSubmissions = submissions.totalCount

		[user: user, watched: watched, roleNames: roleNames, submissions: submissions]
	}

	/**
	 * User delete action. Before removing an existing user,
	 * he should be removed from those authorities which he is involved.
	 */
	def delete = {

		def user = User.get(params.id)
		if (user) {
			def authPrincipal = authenticateService.principal()
			//avoid self-delete if the logged-in user is an admin
			if (!(authPrincipal instanceof String) && authPrincipal.username == user.username) {
				flash.message = "You can not delete yourself, please login as another admin and try again"
			}
			else {
				//first, delete this user from People_Authorities table.
				Role.findAll().each { it.removeFromPeople(user) }
				user.delete()
				flash.message = "User $params.id deleted."
			}
		}
		else {
			flash.message = "User not found with id $params.id"
		}

		redirect action: list
	}

	def edit = {

		def user = User.get(params.id)
		if (!user) {
			flash.message = "User not found with id $params.id"
			redirect action: list
			return
		}

		return buildUserModel(user)
	}

	/**
	 * User update action.
	 */
	def update = {

		def user = User.get(params.id)
		if (!user) {
			flash.message = "User not found with id $params.id"
			redirect action: edit, id: params.id
			return
		}

		long version = params.version.toLong()
		if (user.version > version) {
			user.errors.rejectValue 'version', "user.optimistic.locking.failure",
				"Another user has updated this User while you were editing."
				render view: 'edit', model: buildUserModel(user)
			return
		}

		def oldPassword = user.passwd
		user.properties = params
		if (!params.passwd.equals(oldPassword)) {
			user.passwd = authenticateService.encodePassword(params.passwd)
		}
		if (user.save()) {
			Role.findAll().each { it.removeFromPeople(user) }
			addRoles(user)
			redirect action: show, id: user.id
		}
		else {
			render view: 'edit', model: buildUserModel(user)
		}
	}

	def create = {
		[user: new User(params), authorityList: Role.list()]
	}

	/**
	 * User save action.
	 */
	def save = {

		def user = new User()
		user.properties = params
		user.passwd = authenticateService.encodePassword(params.passwd)
		if (user.save()) {
			addRoles(user)
			redirect action: show, id: user.id
		}
		else {
			render view: 'create', model: [authorityList: Role.list(), user: user]
		}
	}

	private void addRoles(user) {
		for (String key in params.keySet()) {
			if (key.contains('ROLE') && 'on' == params.get(key)) {
				Role.findByAuthority(key).addToPeople(user)
			}
		}
	}

	private Map buildUserModel(user) {

		List roles = Role.list()
		roles.sort { r1, r2 ->
			r1.authority <=> r2.authority
		}
		Set userRoleNames = []
		for (role in user.authorities) {
			userRoleNames << role.authority
		}
		LinkedHashMap<Role, Boolean> roleMap = [:]
		for (role in roles) {
			roleMap[(role)] = userRoleNames.contains(role.authority)
		}

		return [user: user, roleMap: roleMap]
	}
}
