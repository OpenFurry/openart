package com.mjs_svc.openfurry

class IssueVote {
    Person voter
    Issue issue

    static constraints = {
    }

    static belongsTo = Issue
}
