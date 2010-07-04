package openfurry

class OrderedCollection {
    String title
    String description
    SortedSet userObjects

    static constraints = {
        title(maxSize: 120, blank: false)
        description(maxSize: 5000, blank: true)
    }

    static hasMany = [userObjects: UserObject]
}
