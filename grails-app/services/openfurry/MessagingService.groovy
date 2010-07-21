package openfurry

class MessagingService {

    static transactional = true

    def flash(String type, String message) {
        flash.messages = flash.messages ?: []
        flash.messages.add([type: type, message: message])
    }

    def message(Person user, Integer type, String message) {
        user.addToMessages(
            new UserMessage(message: message, type: type))
            .save()
    }
}
