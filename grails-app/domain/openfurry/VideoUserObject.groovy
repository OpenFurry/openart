package openfurry

class VideoUserObject {
    String file

    static constraints = {
        file(blank: false)
    }
}
