package openfurry

class UnitPrice {
    String objectType
    String objectAction
    Integer price

    static constraints = {
        objectType(maxSize: 120, blank: false)
        price()
    }
}
