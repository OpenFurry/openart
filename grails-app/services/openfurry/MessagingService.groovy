package openfurry

class MessagingService {

    static transactional = true

    /*
    Use this for messaging unauthenticated users
    */
    def flash(String type, String message) {
        flash.messages = flash.messages ?: []
        flash.messages.add([type: type, message: message])
    }

    /*
    Message a user with a flash-type message (dismiss on read)
    */
    def message(Person user, Integer type, String message) {
        user.addToMessages(
            new UserMessage(message: message, type: type))
            .save()
    }

    /*
    Message a user with a persistent message (no dismiss on read)
    */
    def persistentMessage(Person user, Integer type, String message) {
        user.addToMessages(
            new UserMessage(message: message, type: type, dismissOnRead: false))
            .save()
    }

    def userMessages(Person user) {
        // Grab all the pertinent messages
        def list = UserMessage.withCriteria {
            eq("userTo", user)
            order("type", "desc")
        }
        
        // Pull the data into a list of maps
        def messages = list.collect {
            [type: it.type, message: it.message, dismissOnRead: it.dismissOnRead]
        }

        // Delete the ones that are marked as dismissOnRead
        list.each {
            if (it.dismissOnRead) {
                it.delete()
            }
        }
        
        // return the list of maps
        messages
    }
}
