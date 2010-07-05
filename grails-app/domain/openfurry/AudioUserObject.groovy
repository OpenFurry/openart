package openfurry

class AudioUserObject extends UserObject {
    String file

    static constraints = {
        file(blank: false)
    }
}
