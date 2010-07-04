package openfurry

class TextUserObject {
    String text
    String attachmentFile
    Boolean journal = false

    static constraints = {
        text(blank: false)
        attachmentFile(blank: true, nullable: true)
    }

    static mapping = {
        text type: "text"
    }
}
