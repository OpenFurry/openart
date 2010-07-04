package openfurry

class Collection {
    
    String title
    String description

    static constraints = {
        title(maxSize: 120, blank: false)
        description(maxSize: 5000, blank: true)
    }

    static hasMany = [userObjects: UserObject]
}
