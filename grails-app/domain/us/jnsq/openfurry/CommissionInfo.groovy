package us.jnsq.openfurry

class CommissionInfo {
    User owner
    String type
    String medium
    String price
    String description
    String example
    Integer maxApplicants
    Boolean open = true

    static constraints = {
        type(inList: ["audio", "video", "flash", "image", "text", "application"])
        price(maxSize: 20)
        description(maxSize: 5000)
        example(nullable: true)
        maxApplicants(nullable: true)
    }
}
