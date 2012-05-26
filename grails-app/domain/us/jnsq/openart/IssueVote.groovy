package us.jnsq.openart

class IssueVote {
    User voter
    Issue issue

    static constraints = {
    }

    static belongsTo = Issue
}
