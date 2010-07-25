package openfurry

class UserMessage {
    String message
    Integer type
    Person userTo
    Boolean dismissOnRead = true

    static constraints = {
        message(maxSize: 1000)
        type(range: 0..3)
    }
}
