package us.jnsq.openart

class IssueVote {
    OAUser voter
    Issue issue

    static constraints = {
    }

    static belongsTo = Issue
}
