package openfurry

class MessagingService {

    static transactional = false

    def flash(String type, String code, String defaultString) {
        flash.messages = flash.messages ?: []
        flash.messages.add([type: type, message: message(code: code, default: defaultString)])
    }

}
