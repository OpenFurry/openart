package openfurry

class Issue {
    String title
    String description
    Long votes
    STring status = "Suggestion"
    String JIRAIssue

    static constraints = {
        title(maxSize: 60, blank: false)
        description(maxSize: 5000, blank: false)
        status(inList: ["Suggestion", "Submitted", "Accepted", "Completed", "Rejected"])
        JIRAIssue(maxSize: 15)
    }
}
