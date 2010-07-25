package openfurry

class MessagingTagLib {
    
    static namespace = "of"

    def messagingService

    def authenticateService

    def messagesForUser = { attrs ->
        def user = Person.findByUsername(authenticateService.principal().username)
        if (user) {
            def toReturn = new StringBuffer()
            messagingService.userMessages().each {
                toReturn.append("<div class=\"${it.type}\">${it.message}</div>")
            }

            out << toReturn.toString()
        } else {
            out << ""
        }
    }
}
