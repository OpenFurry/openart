package com.mjs_svc.openfurry

class UserTrinket {
    String name
    User userFrom
    User owner
    Trinket trinket
    Date dateCreated

    static constraints = {
        name(maxSize: 120, blank: true, nullable: true)
        userFrom(nullable: true)
    }
}
