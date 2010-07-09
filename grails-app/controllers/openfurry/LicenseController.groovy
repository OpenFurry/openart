package openfurry

class LicenseController {

    def scaffold = License

    def messagingService
    def authenticateService

    def mtest = {
        def m = new UserMessage(
            recipient: authenticateService.userDomain(),
            message: "Test!",
            type: 'error'
            )
        m.save(flush: true)
        redirect(uri: '/')
    }
}
