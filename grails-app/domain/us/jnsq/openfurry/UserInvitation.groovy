package us.jnsq.openfurry

class UserInvitation {
    String code

    static constraints = {
        code(maxSize: 10)
    }
}
