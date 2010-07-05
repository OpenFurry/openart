package openfurry

class UnitPrice {
    String objectType
    Integer price

    static constraints = {
        objectType(maxSize: 120, blank: false)
        price()
    }
}
