package openfurry

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

class UserObject implements Comparable {
    
    String title
    String thumbnail = null
    String description
    String externalLink
    Date dateCreated
    Date lastUpdated
    Boolean published = true
    Boolean friendsOnly = false
    Integer rating = CH.config.openfurry.ratings.low
    String tags
    Integer weight = 0
    Long viewCount = 0
    Long favoriteCount = 0
    Collection collection
    Person owner
    License license

    static constraints = {
        title(maxSize: 120, blank: false)
        thumbnail(blank: true, nullable: true)
        description(maxSize: 5000, blank: true, nullable: true)
        externalLink(url: true, blank: true, nullable: true)
        dateCreated(nullable: true)
        lastUpdated(nullable: true)
        rating(range: 0..2)
        tags(blank: true)
        collection(blank: true, nullable: true)
    }

    static mapping = {
        tablePerHierarchy false
    }

    static hasMany = [comments: Comment, species: Species, categories: Category]
    
    static belongsTo = Person

    int compareTo(obj) {
        weight.compareTo(obj.weight)
    }

    String toString() {
        this.title
    }
}
