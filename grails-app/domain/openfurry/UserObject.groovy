package openfurry

class UserObject implements Comparable {
    
    String title
    String thumbnail = "defaultThumb.jpg"
    String description
    Boolean published = true
    Boolean friendsOnly = false
    String rating = "General"
    String tags
    Integer weight = 0
    Long viewCount = 0
    Long favoriteCount = 0
    Collection collection
    Person owner

    static constraints = {
        title(maxSize: 120, blank: false)
        description(maxSize: 5000, blank: true)
        rating(inList: ["General", "Mature", "Adult"])
        tags(blank: true)
        collection(blank: true, nullable: true)
    }

    static mapping = {
        tablePerHierarchy false
    }

    static hasMany = [comments: Comment]
    
    static belongsTo = Person

    int compareTo(obj) {
        weight.compareTo(obj.weight)
    }
}
