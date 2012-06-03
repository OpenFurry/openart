package us.jnsq.openart

class Comment {
    String parentType
    String parentId
    OAUser owner
    Comment parentComment
    String title
    String comment
    Boolean flagged = false
    Boolean visible = true
    Date dateCreated
    Date lastUpdated

    static constraints = {
        parentType(maxSize: 60, blank: false)
        parentId(maxSize: 60, blank: false)
        title(maxSize: 120, blank: true)
        comment(maxSize: 5000, blank: false)
    }

    static hasMany = [children: Comment]

    static belongsTo = Comment
}
