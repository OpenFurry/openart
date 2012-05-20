package us.jnsq.openfurry

class UserStatus {
    String status
    User owner

    static constraints = {
        status(maxSize: 140)
    }
}
