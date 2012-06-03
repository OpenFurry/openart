package us.jnsq.openart

class UserMessage {
    String code
    String defaultMessage
    String regardingType
    Long regardingId
    Integer type
    OAUser regardingUser
    OAUser userTo
    Boolean dismissOnRead = true
    Date dateCreated

    static constraints = {
        code(maxSize: 1000)
        defaultMessage(maxSize: 1000)
        regardingType(maxSize: 60, blank: true, nullable: true)
        regardingId(nullable: true)
        type(range: 0..3)
        regardingUser(nullable: true)
    }

    static belongsTo = OAUser

    static transients = ['argumentString']

    String argumentString() {
        if (regardingType && regardingId) {
            Class.forName("us.jnsq.openart.${this.regardingType}", true, Thread.currentThread().getContextClassLoader())
                .get(this.regardingId)
                .toString()
        }
    }

}
