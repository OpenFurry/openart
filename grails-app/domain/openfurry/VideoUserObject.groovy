package openfurry

class VideoUserObject extends UserObject {
    String file

    static constraints = {
        file(blank: false)
    }
}
