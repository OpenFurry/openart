package com.mjs_svc.openfurry

class MessagingService {

    static transactional = true

    /*
    Use this for messaging unauthenticated users
    */
    def flash(String type, String message) {
        flash.messages = flash.messages ?: []
        flash.messages.add([type: type, message: message])
    }

    /*
    Message a user with a flash-type message (dismiss on read)
    */
    def transientMessage(Person user, Integer type, String code, String defaultMessage, String regardingType = null, Long regardingId = null) {
        user.addToMessages(
            new UserMessage(code: code, defaultMessage: defaultMessage, type: type, regardingType: regardingType, regardingId: regardingId))
            .save(flush: true)
    }

    /*
    Message a user with a persistent message (no dismiss on read)
    */
    def persistentMessage(Person user, Integer type, String code, String defaultMessage, String regardingType = null, Long regardingId = null) {
        user.addToMessages(
            new UserMessage(code: code, defaultMessage: defaultMessage, type: type, regardingType: regardingType, regardingId: regardingId, dismissOnRead: false))
            .save(flush: true)
    }

    def userTransientMessages(Person user) {
        user = Person.get(user.id)
        // Grab all the pertinent messages
        def list = UserMessage.withCriteria {
            and {
                eq("userTo", user)
                eq("dismissOnRead", true)
            }
            order("type", "desc")
        }
        
        // Pull the data into a list of maps
        def messages = list.collect {
            [ 
                type: it.type,
                code: it.code,
                defaultMessage: it.defaultMessage,
                argument: it.argumentString()
            ]
        }

        // Delete the ones that are marked as dismissOnRead
        list.each {
            if (it.dismissOnRead) {
                user.removeFromMessages(it)
                it.delete()
            }
        }
        
        // return the list of maps
        messages
    }

    def userPersistentMessages(Person user) {
        // Return all pertinent messages
        UserMessage.withCriteria {
            and {
                eq("userTo", user)
                eq("dismissOnRead", false)
            }
            order("regardingType", "desc")
        }
    }

    def argumentString(UserMessage message) {
        Class.forName("openfurry.${message.regardingType}", true, Thread.currentThread().getContextClassLoader())
            .get(message.regardingId)
            .toString()
    }
}
