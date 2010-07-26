package openfurry

class Event {
    String title
    String description
    Date eventDateStart
    Date eventDateEnd
    Date dateCreated
    Date lastUpdated
    UserGroup group
    Person owner

    static constraints = {
    }
}
