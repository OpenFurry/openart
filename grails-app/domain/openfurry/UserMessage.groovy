package openfurry

class UserMessage {
    String message
    Integer type
    Person userTo

    static constraints = {
        message(maxSize: 1000)
        type(range: 0..2)
    }
}
