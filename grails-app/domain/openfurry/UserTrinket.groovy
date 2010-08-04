package openfurry

class UserTrinket {
    String name
    Person userFrom
    Person owner
    Trinket trinket
    Date dateCreated

    static constraints = {
        name(maxSize: 120, blank: true, nullable: true)
        userFrom(nullable: true)
    }
}
