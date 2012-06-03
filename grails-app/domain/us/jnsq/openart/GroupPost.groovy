package us.jnsq.openart

class GroupPost {
    OAUser owner
    UserGroup group
    String title
    String post
    Boolean flagged = false
    Boolean visible = true
    Date dateCreated
    Date lastUpdated

    static constraints = {
        title(maxSize: 120)
        post(maxSize: 500)
    }

    static mapping = {
        sort dateCreated: "desc"
    }

    static hasMany = [children: Comment]

    static belongsTo = UserGroup
}
