package openfurry

class ProfileController {
    def authenticateService

    def edit = {
        def person = Person.get(authenticateService.principal().domainClass.id)
        [person: person]
    }
}
