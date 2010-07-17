package openfurry

class GroupPost {
    Person owner
    Group group
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

    static hasMany = [children: Comment]

    static belongsTo = Group
}
