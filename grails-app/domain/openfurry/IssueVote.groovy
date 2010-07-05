package openfurry

class IssueVote {
    Person voter
    Issue issue

    static constraints = {
    }

    static belongsTo = Person
}
