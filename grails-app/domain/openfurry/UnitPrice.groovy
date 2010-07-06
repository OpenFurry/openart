package openfurry

class UnitPrice {
    String component
    String params
    String action
    Integer price

    static constraints = {
        component(maxSize: 120, blank: false)
        params(maxSize: 250, blank: true, nullable: true)
        action(maxSize: 60, blank: false)
        price()
    }
}
