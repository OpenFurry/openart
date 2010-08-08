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
    def transientMessage(User user, Integer type, String code, String defaultMessage, String regardingType = null, Long regardingId = null) {
        user.addToMessages(
            new UserMessage(code: code, defaultMessage: defaultMessage, type: type, regardingType: regardingType, regardingId: regardingId))
            .save(flush: true)
    }

    /*
    Message a user with a persistent message (no dismiss on read)
    */
    def persistentMessage(User user, Integer type, String code, String defaultMessage, String regardingType = null, Long regardingId = null, User regardingUser = null) {
        user.addToMessages(
            new UserMessage(
                code: code, 
                defaultMessage: defaultMessage, 
                type: type, 
                regardingType: regardingType, 
                regardingId: regardingId, 
                regardingUser: regardingUser, 
                dismissOnRead: false
            )
        ).save(flush: true)
    }
    def persistentMessageRole(String authority, Integer type, String code, String defaultMessage, String regardingType = null, Long regardingId = null, User regardingUser = null) {
        def role = Role.findByAuthority(authority)
        if (role) {
            for (user in role.people) {
                persistentMessage(user, type, code, defaultMessage, regardingType, regardingId, regardingUser)
            }
        }
    }

    def userTransientMessages(User user) {
        user = User.get(user.id)
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
                argument: it.argumentString(),
                regardingUser: it.regardingUser
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

    def userPersistentMessages(User user) {
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
        Class.forName("com.mjs_svc.openfurry.${message.regardingType}", true, Thread.currentThread().getContextClassLoader())
            .get(message.regardingId)
            .toString()
    }
}
