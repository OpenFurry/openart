package openfurry

class Issue {
    String title
    String description
    Long votes
    Integer status
    Integer type
    String JIRAIssue

    static constraints = {
        title(maxSize: 60, blank: false)
        description(maxSize: 5000, blank: false)
        status(range: 0..4)
        type(range:0..2)
        JIRAIssue(maxSize: 15, blank: true, nullable: true)
    }
}
