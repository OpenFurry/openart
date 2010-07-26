package openfurry

class IssueController {
    def authenticateService
    def marketService

    static defaultAction = "list"

    def list = {
        def list = Issue.withCriteria {
            and {
                if (params.status) {
                    eq("status", Integer.parseInt(params.status))
                }
                if (params.type) {
                    eq("type", Integer.parseInt(params.type))
                }
            }
            if (params['sort']) {
                order(params['sort'], params.order ?: 'asc')
            }
        }

        [issueList: list]
    }

    def show = {
        def issue = Issue.get(Integer.parseInt(params.id))
        if (!issue) {
            // TODO i18n
            response.sendError(404)
            return
        }

        [issue: issue]
    }

    def create = {
        if (params.id) {
            params.id = null
        }
    }

    def save = {
        def issueInstance
        if (params.id) {
            issueInstance = Issue.get(params.id)
        } else {
            issueInstance = new Issue()
        }
        issueInstance.properties = params
        def owner = authenticateService.principal().domainClass
        issueInstance.submitter = owner
        issueInstance.votes = 1

        if (issueInstance.save(flush: true)) {
            if (!params.id) {
                def issueVoteInstance = new IssueVote()
                issueVoteInstance.voter = owner
                issueVoteInstance.issue = issueInstance
                issueVoteInstance.save(flush: true)
            }
            redirect(action: "show", params: [id: issueInstance.id])
        } else {
            render(view: "create", model: [instance: issueInstance])
        }
    }

    def edit = {
        if (!authenticateService.ifAnyGranted("ROLE_ADMIN,ROLE_STAFF,ROLE_GOVERNOR")) {
            response.sendError(403) // TODO i18n
        }
        def issueInstance = Issue.get(params.id)
        if (issueInstance) {
            render(view: "create", model: [instance: issueInstance])
        } else {
            response.sendError(404) // TODO i18n
        }
    }

    def vote = {
        def issue = Issue.get(params.id)
        if (!issue) {
            response.sendError(404) // TODO i18n
            return
        }
        def person = authenticateService.principal().domainClass

        // Make sure user hasn't already voted on the issue
        def issueVote = IssueVote.withCriteria {
            and {
                eq('issue', issue)
                eq('voter', person)
            }
        }
        if(issueVote) {
            // TODO let user know that they can't vote twice
            render(view: 'show', model: [issue: issue])
            return
        }

        // Increment vote count
        issue.votes++
        issue.save()

        // Create issue vote
        issueVote = new IssueVote(
            issue: issue,
            voter: person
        )
        issueVote.save()

        // charge the user
        marketService.transact(person, "Issue.vote(memberClass:${person.memberClass})")

        // Show success, message, issue
        render(view: 'show', model: [issue: issue])
    }
}
