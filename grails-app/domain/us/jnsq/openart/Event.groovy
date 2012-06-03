package us.jnsq.openart

class Event {
    String title
    String description
    String location
    Date eventDateStart
    Date eventDateEnd
    Date dateCreated
    Date lastUpdated
    UserGroup group
    OAUser owner

    static constraints = {
        description(maxSize: 5000)
        location(blank: true, nullable: true)
    }
}
