package com.mjs_svc.openfurry

class Event {
    String title
    String description
    String location
    Date eventDateStart
    Date eventDateEnd
    Date dateCreated
    Date lastUpdated
    UserGroup group
    Person owner

    static constraints = {
        description(maxSize: 5000)
        location(blank: true, nullable: true)
    }
}
