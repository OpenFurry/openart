package us.jnsq.openfurry

class GroupRequest {
    User requester
    UserGroup group
    String requestersReason
    String adminsReason
    Date dateCreated

    static constraints = {
        requestersReason(maxSize: 1000, blank: true, nullable: true)
        adminsReason(maxSize: 1000, blank: true, nullable: true)
    }
}
