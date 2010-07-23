package openfurry

class Flatpage {
    String slug
    String locale = "en_US"
    String title
    String content
    String layout = "main"

    static constraints = {
        slug(unique: true, blank: false)
        title(blank: false)
        content(blank: false)
        layout(blank: false)
    }

    static mapping = {
        content type: "text"
    }
}
