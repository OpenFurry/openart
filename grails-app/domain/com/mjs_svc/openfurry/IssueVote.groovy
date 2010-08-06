package com.mjs_svc.openfurry

class IssueVote {
    User voter
    Issue issue

    static constraints = {
    }

    static belongsTo = Issue
}
