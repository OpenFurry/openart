package openfurry

class AudioUserObject {
    String file

    static constraints = {
        file(blank: false)
    }
}
