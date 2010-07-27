package openfurry

class SearchController {
    def listService
    def permissionsService

    static defaultAction = "index"

    def index = { }
    
    def submissions = {
        def searchTerm
        if (!params.q) {
            redirect(action: "index")
        } else {
            searchTerm = "%${params.q}%"
        }
        def criteria = {
            or {
                ilike("title", searchTerm)
                ilike("description", searchTerm)
                if (params.includeTags) {
                    tags {
                        ilike("tag", searchTerm)
                    }
                }
                if (params.includeCategories) {
                    categories {
                        ilike("categoryName", searchTerm)
                    }
                }
                if (params.includeSpecies) {
                    species {
                        ilike("speciesName", searchTerm)
                    }
                }
            }
        }

        def list = listService.listUOsForRating(criteria, null)

        [uoList: list, searchTerm: params.q]
    }

    def users = {
        def list = Person.withCriteria {}

        [people: list]
    }
    
    def issues = {
        def searchTerm
        if (!params.q) {
            redirect(action: "index")
        } else {
            searchTerm = "%${params.q}%"
        }

        def list = Issue.withCriteria {
            or {
                ilike("title", searchTerm)
                ilike("description", searchTerm)
            }
        }

        [issueList: list, searchTerm: params.q]
    }

    def groups = {
        def list = UserGroup.withCriteria {}

        [groups: list]
    }

    def posts = {
        def list = GroupPost.withCriteria {}

        [posts: list]
    }

    def events = {
        def list = Event.withCriteria {}

        [events: list]
    }
}
