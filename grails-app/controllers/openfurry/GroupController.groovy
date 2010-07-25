package openfurry

class GroupController {
    static defaultAction = "list"

    def authenticateService

    def permissionsService

    def list = {
        [groups: UserGroup.list()]
    }

    def show = {
        def group = UserGroup.findBySlug(params.id)

        if (!group) {
            response.sendError(404) //TODO i18n
            return
        }
        
        [group: group]
    }

    def thread = {
        def thread = GroupPost.get(params.id)
        
        if (!thread) {
            response.sendError(404) // TODO i18n
            return
        }

        if (!permissionsService.groups.userCanRead(thread.group)) {
            response.sendError(403) // TODO i18n
            return
        }

        [thread: thread]
    }

    def post = {
        def group = UserGroup.findBySlug(params.id)

        if (!permissionsService.groups.userCanPost(group)) {
            response.sendError(403) // TODO i18n
            return
        }

        [groupId: group.id]
    }

    def savePost = {
        def group = UserGroup.get(params.groupId)
        def owner = authenticateService.principal().domainClass

        if (!permissionsService.groups.userCanPost(group)) {
            response.sendError(403)
            return
        }

        def post = new GroupPost()
        post.properties = params
        post.group = group
        post.owner = owner

        if (post.save(flush: true)) {
            // TODO notify all members, transact

            redirect(action: 'thread', id: post.id)
        } else {
            redirect(action: 'post', model: [instance: post])
        }
    }

    def deletePost = {
        def post = GroupPost.get(params.id)

        if (!post) {
            response.sendError(404) // TODO i18n
            return
        }

        if (!permissionsService.groups.userCanDeletePost(post)) {
            response.sendError(403) // TODO i18n
            return
        }
        
        def groupId = post.group.id
        post.delete()

        // TODO message

        redirect(action: 'show', id: groupId)
    }

    def create = {
        if (params.id) {
            params.id = null
        }
    }

    def edit = {
        def group = UserGroup.findBySlug(params.id)

        if (!group) {
            response.sendError(404) //  TODO i18n
            return
        }

        if (!permissionsService.groups.userCanEditGroup(group)) {
            response.sendError(403) // TODO i18n
            return
        }

        render(view: "create", model: [instance: group]) 
    }

    def save = {
        def groupInstance
        if (params.id) {
            groupInstance = UserGroup.get(params.id)
        } else {
            groupInstance = new UserGroup()
        }
        groupInstance.properties = params
        groupInstance.admin = authenticateService.principal().domainClass

        if (groupInstance.save(flush: true)) {
            // TODO message, transact

            redirect(action: 'show', id: groupInstance.slug)
        } else {
            render(view: 'create', model: [instance: groupInstance])
        }
    }

    def delete = {}

}
