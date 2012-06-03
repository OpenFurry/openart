package us.jnsq.openart

class GroupRequest {
    OAUser requester
    UserGroup group
    String requestersReason
    String adminsReason
    Date dateCreated

    static constraints = {
        requestersReason(maxSize: 1000, blank: true, nullable: true)
        adminsReason(maxSize: 1000, blank: true, nullable: true)
    }
}
