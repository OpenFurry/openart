package openfurry

class UserMessage {
    String message
    Integer type
    Person user
    Person sender

    static constraints = {
        message(maxSize: 1000, blank: false)
        type(range: 0..2, blank: false)
        sender(blank: true, nullable: true)
    }
}
