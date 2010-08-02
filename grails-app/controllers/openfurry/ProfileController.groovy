package openfurry

class ProfileController {
    def authenticateService

    def edit = {
        def person = Person.get(authenticateService.principal().domainClass.id)
        [person: person]
    }

    def save = {
        def user = Person.get(authenticateService.principal().domainClass.id)

        user.properties = params
        if (params.passwd && params.passwd != "") {
            if (params.pass != params.passwd) {
                user.error.rejectValue("passwd", "openfurry.errors.passwordMismatch", "The passwords you entered did not match")
            } else {
                user.passwd = authenticateService.encodePassword(params.passwd)
            }
        }

        if (params.newspecies && params.newspecies != user.species.id) {
            user.species = Species.get(params.newspecies)
        }

        if (user.save(flush: true)) {
            // TODO message user

            redirect(controller: "person", action: "show", params: [username: user.username])
        } else {
            render(view: "edit", model: [person: user])
        }
    }

    def props = {
        def user = Person.get(authenticateService.principal().domainClass.id)
        [user: user]
    }

    def addProperty = {
        def prop = new UserProperty()
        prop.properties = params
        prop.user = authenticateService.principal().domainClass
        if (prop.save(flush: true)) {
            // TODO message user
            redirect(action: "props")
        } else {
        }
    }

    def deleteProperty = {
    }
}
