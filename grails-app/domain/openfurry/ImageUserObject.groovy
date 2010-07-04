package openfurry

class ImageUserObject {
    String sizedFile
    String fullFile

    static constraints = {
        sizedFile(blank: false)
        fullFile(blank: false)
    }
}
