package openfurry

class ProfileController {
    def authenticateService

    def edit = {
        def person = Person.findByUsername(authenticateService.principal().username)
        [person: person]
    }
}
