package us.jnsq.openart

class WatchController {

    static defaultAction = 'list'

    def authenticateService
    def messagingService
    def listService
    def refreshUserService

    def list = {
        def user = User.get(authenticateService.principal().domainClass.id)
        def criteria = {
            or {
                'in'('owner', user.watches)
                if (user.watchedTags.size() > 0) {
                    tags {
                        or {
                            for (t in user.watchedTags) {
                                eq('tag', t.tag)
                            }
                        }
                    }
                }
            }
        }

        def list = listService.listUOsForRating(criteria, params)
        
        [uoList: list]
    }

    def updateCursors = {
        def user = User.get(authenticateService.principal().domainClass.id)
        user.watchlistCursorPenultimate = user.watchlistCursorUltimate.clone()
        user.watchlistCursorUltimate = new Date()

        if (user.watchlistCursorBookmarkDate?.before(user.watchlistCursorUltimate)) {
            user.watchlistCursorBookmark = null
            user.watchlistCursorBookmarkDate = null
        }

        if (user.save(flush: true)) {
            refreshUserService.refresh()
            redirect(action: "list", params: params)
        } else {
            response.sendError(500)
        }
    }

    def bookmark = {
        def user = User.get(authenticateService.principal().domainClass.id)
        def submission = UserObject.get(params.id)

        if (!submission.lastUpdated.before(user.watchlistCursorUltimate)) {
            user.watchlistCursorBookmarkDate = new Date()
            user.watchlistCursorBookmark = submission.lastUpdated
            user.save(flush: true)
        }

        refreshUserService.refresh()
        redirect(action: "list", params: params)
    }
    
    def addUser = {
        def user = User.get(authenticateService.principal().domainClass.id)
        def toAdd = User.findByUsername(params.id)

        if (!toAdd) {
            response.sendError(404) // TODO i18n
            return
        }

        for (w in user.watches) {
            if (toAdd == w) {
                messagingService.transientMessage(
                    user,
                    grailsApplication.config.openart.user.messageTypes.warning,
                    "openart.messages.alreadyWatching",
                    "You're already watching {0}!",
                    User.class.toString().split("\\.")[-1],
                    toAdd.id)
                return
            }
        }

        user.addToWatches(toAdd).save()

        messagingService.transientMessage(
            user,
            grailsApplication.config.openart.user.messageTypes.success,
            "openart.messages.watching",
            "You've watched {0}",
            User.class.toString().split("\\.")[-1],
            toAdd.id)
        messagingService.persistentMessage(
            toAdd,
            grailsApplication.config.openart.user.messageTypes.success,
            "openart.messages.watched",
            "You've been watched by {0}",
            User.class.toString().split("\\.")[-1],
            user.id)
    }
    
    def addTag = {
        def user = User.get(authenticateService.principal().domainClass.id)
        def tag = Tag.findByTag(params.id)

        if (!tag) {
            response.sendError(404) // TODO i18n
            return
        }

        for (w in user.watchedTags) {
            if (tag.tag == w.tag) {
                // TODO flash user to let them know they're already watching that tag
                return
            }
        }

        user.addToWatchedTags(tag).save(flush: true)

        // TODO flash user
    }

    def removeUser = {
        def user = User.get(authenticateService.principal().domainClass.id)
        def toRemove = User.findByUsername(params.id)

        if (!toRemove) {
            response.sendError(404) // TODO i18n
            return
        }

        user.removeFromWatches(toRemove).save(flush: true)

        // TODO flash user
    }
    
    def removeTag = {
        def user = User.get(authenticateService.principal().domainClass.id)
        def tag = Tag.findByTag(params.id)

        if (!tag) {
            response.sendError(404) // TODO i18n
            return
        }

        user.removeFromWatchedTags(tag).save(flush: true)

        // TODO flash user
    }

    def addFriend = {
        def user = User.get(authenticateService.principal().domainClass.id)
        def toAdd = User.findByUsername(params.id)

        if (!toAdd) {
            response.sendError(404) // TODO i18n
            return
        }

        for (f in user.friends) {
            if (toAdd == f) {
                messagingService.transientMessage(
                    user,
                    grailsApplication.config.openart.user.messageTypes.warning,
                    "openart.messages.alreadyFriended",
                    "You've already friended {0}!",
                    User.class.toString().split("\\.")[-1],
                    toAdd.id)
                return
            }
        }

        user.addToFriends(toAdd).save()

        messagingService.transientMessage(
            user,
            grailsApplication.config.openart.user.messageTypes.success,
            "openart.messages.friend",
            "You've watched {0}",
            User.class.toString().split("\\.")[-1],
            toAdd.id)
        messagingService.persistentMessage(
            toAdd,
            grailsApplication.config.openart.user.messageTypes.success,
            "openart.messages.friended",
            "You've been watched by {0}",
            User.class.toString().split("\\.")[-1],
            user.id)
    }

    def removeFriend = {
        def user = User.get(authenticateService.principal().domainClass.id)
        def toRemove = User.findByUsername(params.id)

        if (!toRemove) {
            response.sendError(404) // TODO i18n
            return
        }

        user.removeFromFriends(toRemove).save(flush: true)

        // TODO flash user
    }
}
