package com.mjs_svc.openfurry

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

class UserObject implements Comparable {
    
    String type
    String title
    String thumbnail
    String description
    String externalLink
    Date dateCreated
    Date lastUpdated
    Boolean published = true
    Boolean friendsOnly = false
    Boolean freezeComments = false
    Boolean takenDown = false
    Integer rating = CH.config.openfurry.ratings.low
    Integer weight = 0
    Long viewCount = 0
    Long favoriteCount = 0
    User owner
    License license

    static constraints = {
        type(inList: ["audio", "video", "flash", "image", "text", "application", "orderedCollection", "unorderedCollection"])
        title(maxSize: 120, blank: false)
        thumbnail(blank: true, nullable: true)
        description(maxSize: 5000, blank: true, nullable: true)
        externalLink(url: true, blank: true, nullable: true)
        dateCreated(nullable: true)
        lastUpdated(nullable: true)
        rating(range: 0..2)
        tags(nullable: true)
        license(nullable: true)
    }

    static mapping = {
        tablePerHierarchy false
    }

    static hasMany = [comments: Comment, species: Species, categories: Category, tags: Tag]
    
    static belongsTo = User

    int compareTo(obj) {
        weight.compareTo(obj.weight)
    }

    String toString() {
        this.title
    }
}
