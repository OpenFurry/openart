package openfurry

class WatchController {

    static defaultAction = 'list'

    def authenticateService
    def messagingService
    def listService

    def list = {
        def person = Person.get(authenticateService.principal().domainClass.id)
        def criteria = {
            or {
                'in'('owner', person.watches)
                if (person.watchedTags.size() > 0) {
                    tags {
                        or {
                            for (t in person.watchedTags) {
                                eq('tag', t.tag)
                            }
                        }
                    }
                }
            }
        }

        def list = listService.listUOsForRating(criteria, params.type)
        
        [uoList: list]
    }
    
    def addUser = {
        def person = Person.get(authenticateService.principal().domainClass.id)
        def toAdd = Person.findByUsername(params.id)

        if (!toAdd) {
            response.sendError(404) // TODO i18n
            return
        }

        for (w in person.watches) {
            if (toAdd == w) {
                // TODO flash user to let them know they're already watching that person
                return
            }
        }

        person.addToWatches(toAdd).save()

        // TODO flash person
        messagingService.message(
            toAdd,
            grailsApplication.config.openfurry.user.messageTypes.success,
            message(code: 'openfurry.messages.watched', args: [person.username, person.userRealName], "You've been watched by {1} ({0})"))
        
    }
    
    def addTag = {
        def person = Person.get(authenticateService.principal().domainClass.id)
        def tag = Tag.findByTag(params.id)

        if (!tag) {
            response.sendError(404) // TODO i18n
            return
        }

        for (w in person.watchedTags) {
            if (tag.tag == w.tag) {
                // TODO flash user to let them know they're already watching that tag
                return
            }
        }

        person.addToWatchedTags(tag).save(flush: true)

        // TODO flash person
    }

    def removeUser = {
        def person = Person.get(authenticateService.principal().domainClass.id)
        def toRemove = Person.findByUsername(params.id)

        if (!toRemove) {
            response.sendError(404) // TODO i18n
            return
        }

        person.removeFromWatches(toRemove).save(flush: true)

        // TODO flash person
    }
    
    def removeTag = {
        def person = Person.get(authenticateService.principal().domainClass.id)
        def tag = Tag.findByTag(params.id)

        if (!tag) {
            response.sendError(404) // TODO i18n
            return
        }

        person.removeFromWatchedTags(tag).save(flush: true)

        // TODO flash person
    }
}
