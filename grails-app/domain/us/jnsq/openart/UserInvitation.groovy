package us.jnsq.openart

class UserInvitation {
    String code

    static constraints = {
        code(maxSize: 10)
    }
}
