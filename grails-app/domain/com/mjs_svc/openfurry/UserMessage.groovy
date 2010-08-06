package com.mjs_svc.openfurry

class UserMessage {
    String code
    String defaultMessage
    String regardingType
    Long regardingId
    Integer type
    User userTo
    Boolean dismissOnRead = true
    Date dateCreated

    static constraints = {
        code(maxSize: 1000)
        defaultMessage(maxSize: 1000)
        regardingType(maxSize: 60, blank: true, nullable: true)
        regardingId(blank: true, nullable: true)
        type(range: 0..3)
    }

    static belongsTo = User

    static transients = ['argumentString']

    String argumentString() {
        Class.forName("openfurry.${this.regardingType}", true, Thread.currentThread().getContextClassLoader())
            .get(this.regardingId)
            .toString()
    }

}
