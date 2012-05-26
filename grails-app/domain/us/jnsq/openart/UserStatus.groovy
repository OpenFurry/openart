package us.jnsq.openart

class UserStatus {
    String status
    User owner

    static constraints = {
        status(maxSize: 140)
    }
}
