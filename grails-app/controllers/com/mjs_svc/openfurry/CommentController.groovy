package com.mjs_svc.openfurry

class CommentController {
    // Needed for current user
    def authenticateService
    
    // Needed to notify current user and owner of object
    def messagingService

    // Needed for transaction for comments
    def marketService

    // Needed to prettify comment body
    def markdownService
    def linkingService

    def post = {
        if (!params.objectType) {
            response.sendError(500) // TODO i18n
            return
        }
        def object = Class.forName("com.mjs_svc.openfurry.${params.objectType}", true, Thread.currentThread().getContextClassLoader()).get(Integer.parseInt(params.objectId))
        if (!object) {
            response.sendError(500) // TODO i18n
            return
        }

        def user = authenticateService.principal().domainClass
        def parentComment = null
        if (params.parentId) {
            parentComment = Comment.get(params.parentId)
        }
        
        def comment = new Comment(
            parentType: params.objectType,
            parentId: params.objectId,
            owner: user,
            parentComment: parentComment,
            title: params.title,
            comment: params.comment,
            visible: params.invisible ? false: true
        )
        if (comment.save(flush: true)) {
            // TODO Notify poster and object owner
            messagingService.transientMessage(
                user,
                grailsApplication.config.openfurry.user.messageTypes.success,
                "openfurry.messages.comment.posted",
                "Comment successfully posted"
            )

            def reTypeCode
            def reTypeDefault
            def reType = params.parentType
            def reId = params.parentId
            def owner
            switch (params.objectType) {
                case "UserObject":
                case "AudioUserObject":
                case "VideoUserObject":
                case "FlashUserObject":
                case "ImageUserObject":
                case "TextUserObject":
                case "ApplicationUserObject":
                case "OrderedCollection":
                case "UnorderedCollection":
                    reTypeCode = "openfurry.messages.comment.onUo"
                    reTypeDefault = "{1} has commented on your submission, {0}"
                    owner = object.owner
                    break
                case "GroupPost":
                    reTypeCode = "openfurry.messages.comment.onThread"
                    reTypeDefault = "{1} has commented on your thread, {0}"
                    owner =  object.owner
                    break
                case "User":
                    reTypeCode = "openfurry.messages.comment.onUser"
                    reTypeDefault = "{0} has PM'd you"
                    reType = "User"
                    reId = user.id
                    owner = object
                    break
                default:
                    reTypeCode = "openfurry.messages.comment.default"
                    reTypeDefault = "A comment has been posted on {0} by {1}"
            }
            messagingService.persistentMessage(
                owner,
                grailsApplication.config.openfurry.user.messageTypes.success,
                reTypeCode,
                reTypeDefault,
                reType,
                reId,
                user
            )

            object.save(flush: true) // update object
            if (params.targetURI) {
                redirect(uri: "${params.targetURI}#c${comment.id}")
            } else {
                [comment: comment]
            }
        } else {
            [comment: comment]
        }
    }

    def delete = {
        def comment = Comment.get(params.id)
        
        if (!comment) {
            response.sendError(404)
            return
        }

        if (!permissionsService.comments.userCanDelete(comment)) {
            response.sendError(403)
            return
        }

        // Do this so we can flush
        Comment.withCriteria {
            eq("parentComment", comment)
        }.each {
            _delTree(it)
        }

        comment.delete(flush: true)
    }

    private _delTree(comment) {
        Comment.withCriteria {
            eq("parentComment", comment)
        }.each {
            _delTree(it)
        }
        comment.delete()
    }
}
