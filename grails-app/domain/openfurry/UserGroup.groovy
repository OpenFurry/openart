package openfurry

class UserGroup {
    String slug
    String title
    String description
    Integer category
    Boolean exclusive = false
    Person admin

    static constraints = {
        slug(maxSize: 60)
        title(maxSize: 100)
        description(maxSize: 5000)
    }

    static hasMany = [members: Person, posts: GroupPost, events: Event]
    static belongsTo = Person
}
