package us.jnsq.openfurry

class Trinket {
    String name
    String display
    String description
    UnitPrice price

    static constraints = {
        name(maxSize: 120)
        display(maxSize: 1000)
        description(maxSize: 5000)
    }
}
