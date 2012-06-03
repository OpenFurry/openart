package us.jnsq.openart

class UserGroup {
    String slug
    String title
    String description
    Integer category
    Boolean exclusive = false
    //Long adminId // The id of the User object who created the group
    // See http://jira.codehaus.org/browse/GRAILS-3154 for why this has to be this way

    static constraints = {
        slug(maxSize: 60)
        title(maxSize: 100)
        description(maxSize: 5000)
    }

    static hasMany = [members: OAUser, posts: GroupPost, events: Event]
    static hasOne = [admin: OAUser]
    static belongsTo = OAUser
}
