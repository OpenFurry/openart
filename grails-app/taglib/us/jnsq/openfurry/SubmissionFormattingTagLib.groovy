package us.jnsq.openfurry

class SubmissionFormattingTagLib {
    static namespace = "of"

    def authenticateService
    
    def classForSubmissionListing = { attrs ->
        if (authenticateService.isLoggedIn()) {
            def submissionDate = attrs['submission'].lastUpdated
            def user = authenticateService.principal().domainClass

            if (submissionDate.after(user.watchlistCursorUltimate)) {
                out << "unread"
            }

            if (submissionDate.before(user.watchlistCursorUltimate)
                && submissionDate.after(user.watchlistCursorPenultimate)) {
                out << "prev-unread"
            }

            if (submissionDate.before(user.watchlistCursorPenultimate)) {
                out << "read"
            }

            if ((user.watchlistCursorBookmark && user.watchlistCursorBookmarkDate)
                && submissionDate.before(user.watchlistCursorBookmark)
                && submissionDate.after(user.watchlistCursorBookmarkDate)) {
                out << "bookmarked"
            }
        }
    }
}
