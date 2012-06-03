package us.jnsq.openart

class Issue {
    String title
    String description
    Long votes
    Integer status = 0
    Integer type
    String JIRAIssue
    OAUser submitter
    Date dateCreated

    static constraints = {
        title(maxSize: 60, blank: false)
        description(maxSize: 5000, blank: false)
        status(range: 0..4)
        type(range:0..6)
        JIRAIssue(maxSize: 15, blank: true, nullable: true)
    }
}
