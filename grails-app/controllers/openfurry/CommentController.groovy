package openfurry

class CommentController {
    // Needed for current user
    def authenticateService
    
    // Needed to notify current user and owner of object
    def messagingService

    // Needed for transaction for comments
    def marketService

    def post = {
        if (!params.objectType) {
            response.sendError(500) // TODO i18n
            return
        }
        def object = Class.forName("openfurry.${params.objectType}", true, Thread.currentThread().getContextClassLoader()).get(Integer.parseInt(params.objectId))
        if (!object) {
            response.sendError(500) // TODO i18n
            return
        }

        def person = authenticateService.principal().domainClass
        def parentComment = null
        if (params.parentId) {
            parentComment = Comment.get(params.parentId)
        }
        
        def comment = new Comment(
            parentType: params.objectType,
            parentId: params.objectId,
            owner: person,
            parentComment: parentComment,
            title: params.title,
            comment: params.comment,
            visible: params.invisible ? false: true
        )
        if (comment.save(flush: true)) {
            // TODO Notify poster and object owner

            if (params.targetURI) {
                redirect(uri: "${params.targetURI}#c${comment.id}")
            } else {
                [comment: comment]
            }
        } else {
            [comment: comment]
        }
    }

    def flag = {}
    def delete = {}
}
