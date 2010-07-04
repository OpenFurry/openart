package openfurry

class FlashUserObject {
    String file

    static constraints = {
        file(blank: false)
    }
}
