package us.jnsq.openart

class UserStatus {
    String status
    OAUser owner

    static constraints = {
        status(maxSize: 140)
    }
}
