package openfurry

class CommissionInfo {
    Person owner
    String type
    String medium
    String price
    String description
    String example

    static constraints = {
        type(inList: ["audio", "video", "flash", "image", "text", "application"])
        price(maxSize: 20)
        description(maxSize: 5000)
        example(blank: true, nullable: true)
    }
}
