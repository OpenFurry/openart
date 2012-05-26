package us.jnsq.openart

class InboxController {
    static defaultAction = "index"
    def authenticateService

    def messagingService

    def index = {
        [messages: messagingService.userPersistentMessages(authenticateService.principal().domainClass)]
    }

    def dismiss = {
        def user = User.get(authenticateService.principal().domainClass.id)
        params.id.each {
            def message = UserMessage.get(Long.parseLong(it.getKey().replaceAll('_', '')))
            if (message) {
                user.removeFromMessages(message).save()
                message.delete(flush: true)
            }
        }
        redirect(controller: "inbox")
    }
}
