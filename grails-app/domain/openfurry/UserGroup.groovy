package openfurry

class UserGroup {
    String title
    String description
    Boolean exclusive = false
    Person admin

    static constraints = {
        title(maxSize: 100)
        description(maxSize: 5000)
    }

    static hasMany = [members: Person, posts: GroupPost]
    static belongsTo = Person
}
