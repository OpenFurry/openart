package us.jnsq.openfurry

class IssueVote {
    User voter
    Issue issue

    static constraints = {
    }

    static belongsTo = Issue
}
