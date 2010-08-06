package com.mjs_svc.openfurry

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

        def list = listService.listUOsForRating(criteria, params)
        
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
                messagingService.transientMessage(
                    person,
                    grailsApplication.config.openfurry.user.messageTypes.warning,
                    "openfurry.messages.alreadyWatching",
                    "You're already watching {0}!",
                    Person.class.toString().split("\\.")[-1],
                    toAdd.id)
                return
            }
        }

        person.addToWatches(toAdd).save()

        messagingService.transientMessage(
            person,
            grailsApplication.config.openfurry.user.messageTypes.success,
            "openfurry.messages.watching",
            "You've watched {0}",
            Person.class.toString().split("\\.")[-1],
            toAdd.id)
        messagingService.persistentMessage(
            toAdd,
            grailsApplication.config.openfurry.user.messageTypes.success,
            "openfurry.messages.watched",
            "You've been watched by {0}",
            Person.class.toString().split("\\.")[-1],
            person.id)
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

    def addFriend = {
        def person = Person.get(authenticateService.principal().domainClass.id)
        def toAdd = Person.findByUsername(params.id)

        if (!toAdd) {
            response.sendError(404) // TODO i18n
            return
        }

        for (f in person.friends) {
            if (toAdd == f) {
                messagingService.transientMessage(
                    person,
                    grailsApplication.config.openfurry.user.messageTypes.warning,
                    "openfurry.messages.alreadyFriended",
                    "You've already friended {0}!",
                    Person.class.toString().split("\\.")[-1],
                    toAdd.id)
                return
            }
        }

        person.addToFriends(toAdd).save()

        messagingService.transientMessage(
            person,
            grailsApplication.config.openfurry.user.messageTypes.success,
            "openfurry.messages.friend",
            "You've watched {0}",
            Person.class.toString().split("\\.")[-1],
            toAdd.id)
        messagingService.persistentMessage(
            toAdd,
            grailsApplication.config.openfurry.user.messageTypes.success,
            "openfurry.messages.friended",
            "You've been watched by {0}",
            Person.class.toString().split("\\.")[-1],
            person.id)
    }

    def removeFriend = {
        def person = Person.get(authenticateService.principal().domainClass.id)
        def toRemove = Person.findByUsername(params.id)

        if (!toRemove) {
            response.sendError(404) // TODO i18n
            return
        }

        person.removeFromFriends(toRemove).save(flush: true)

        // TODO flash person
    }
}
