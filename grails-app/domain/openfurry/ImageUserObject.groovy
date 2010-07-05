package openfurry

class ImageUserObject extends UserObject {
    String sizedFile
    String fullFile

    static constraints = {
        sizedFile(blank: false)
        fullFile(blank: false)
    }
}
