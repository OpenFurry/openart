import openfurry.Person
import openfurry.Role

/**
 * User controller.
 */
class PersonController {

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
		[personList: Person.list(params)]
	}

    def friends = {
        def user = Person.findByUsername(params.id)
        if (!user) {
            response.sendError(404)
            return
        }

        [user: user]
    }

	def show = {
		def person = params.username ? Person.findByUsername(params.username) : Person.get(params.id)
		if (!person) {
            response.sendError(404, "Not Found") // i18n
			return
		}
		List roleNames = []
		for (role in person.authorities) {
			roleNames << role.authority.split("_")[1].toLowerCase()
		}
		roleNames.sort { n1, n2 ->
			n1 <=> n2
		}

        def watched = false
        if (authenticateService.isLoggedIn()) {
            def p = Person.findByUsername(authenticateService.principal().username)
            if (person in p.watches) {
                watched = true
            }
        }

        def criteria = {
            eq("owner", person)
        }
        def submissions = listService.listUOsForRating(criteria, params)
        params.totalSubmissions = submissions.totalCount

		[person: person, watched: watched, roleNames: roleNames, submissions: submissions]
	}

	/**
	 * Person delete action. Before removing an existing person,
	 * he should be removed from those authorities which he is involved.
	 */
	def delete = {

		def person = Person.get(params.id)
		if (person) {
			def authPrincipal = authenticateService.principal()
			//avoid self-delete if the logged-in user is an admin
			if (!(authPrincipal instanceof String) && authPrincipal.username == person.username) {
				flash.message = "You can not delete yourself, please login as another admin and try again"
			}
			else {
				//first, delete this person from People_Authorities table.
				Role.findAll().each { it.removeFromPeople(person) }
				person.delete()
				flash.message = "Person $params.id deleted."
			}
		}
		else {
			flash.message = "Person not found with id $params.id"
		}

		redirect action: list
	}

	def edit = {

		def person = Person.get(params.id)
		if (!person) {
			flash.message = "Person not found with id $params.id"
			redirect action: list
			return
		}

		return buildPersonModel(person)
	}

	/**
	 * Person update action.
	 */
	def update = {

		def person = Person.get(params.id)
		if (!person) {
			flash.message = "Person not found with id $params.id"
			redirect action: edit, id: params.id
			return
		}

		long version = params.version.toLong()
		if (person.version > version) {
			person.errors.rejectValue 'version', "person.optimistic.locking.failure",
				"Another user has updated this Person while you were editing."
				render view: 'edit', model: buildPersonModel(person)
			return
		}

		def oldPassword = person.passwd
		person.properties = params
		if (!params.passwd.equals(oldPassword)) {
			person.passwd = authenticateService.encodePassword(params.passwd)
		}
		if (person.save()) {
			Role.findAll().each { it.removeFromPeople(person) }
			addRoles(person)
			redirect action: show, id: person.id
		}
		else {
			render view: 'edit', model: buildPersonModel(person)
		}
	}

	def create = {
		[person: new Person(params), authorityList: Role.list()]
	}

	/**
	 * Person save action.
	 */
	def save = {

		def person = new Person()
		person.properties = params
		person.passwd = authenticateService.encodePassword(params.passwd)
		if (person.save()) {
			addRoles(person)
			redirect action: show, id: person.id
		}
		else {
			render view: 'create', model: [authorityList: Role.list(), person: person]
		}
	}

	private void addRoles(person) {
		for (String key in params.keySet()) {
			if (key.contains('ROLE') && 'on' == params.get(key)) {
				Role.findByAuthority(key).addToPeople(person)
			}
		}
	}

	private Map buildPersonModel(person) {

		List roles = Role.list()
		roles.sort { r1, r2 ->
			r1.authority <=> r2.authority
		}
		Set userRoleNames = []
		for (role in person.authorities) {
			userRoleNames << role.authority
		}
		LinkedHashMap<Role, Boolean> roleMap = [:]
		for (role in roles) {
			roleMap[(role)] = userRoleNames.contains(role.authority)
		}

		return [person: person, roleMap: roleMap]
	}
}
