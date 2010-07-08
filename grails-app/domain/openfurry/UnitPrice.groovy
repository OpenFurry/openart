package openfurry

class UnitPrice {
    String signal
    String description
    Integer price

    static constraints = {
        signal(maxSize: 100, blank: false, unique: true)
        description(maxSize: 500, blank: false)
        price()
    }
}
