package openfurry

class Theme {
    String name
    String description
    String style
    Person creator

    static constraints = {
        name(maxSize: 30, blank: false)
        description(maxSize: 500, nullable: true)
        style(blank: false)
    }

    static mapping = {
        style type: "text"
    }

    String staticFile() {
        "theme/${id}/main.css"
    }
}
