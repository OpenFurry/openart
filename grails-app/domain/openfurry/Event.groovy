package openfurry

class Event {
    String title
    String description
    Date eventDateStart
    Date eventDateEnd
    Date dateCreated
    Date lastModified
    Group group
    Person owner

    static constraints = {
    }
}
