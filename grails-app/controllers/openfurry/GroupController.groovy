package openfurry

class GroupController {
    static defaultAction = "list"

    def authenticateService

    def permissionsService

    def listService

    def list = {
        [groups: UserGroup.list()]
    }

    def show = {
        def group = UserGroup.findBySlug(params.id)

        if (!group) {
            response.sendError(404) //TODO i18n
            return
        }

        def now = new Date()
        def events = Event.withCriteria {
            between("eventDateEnd", now, now + 30)
        }

        def posts = GroupPost.withCriteria {
            maxResults(10)
            order("lastUpdated", "desc")
        }

        [group: group, events: events, posts: posts]
    }

    def join = {
        def group = UserGroup.findBySlug(params.id)
        
        if (!group) {
            response.sendError(404) // TODO i18n
            return
        }

        if (group.exclusive) {
            response.sendError(403)
            return
        }

        def user = Person.get(authenticateService.principal().domainClass.id)
        user.addToGroups(group).save(flush: true)

        // TODO message user, admin

        redirect(action: "show", id: group.slug)
    }

    def requestToJoin = {
        def group = Group.get(params.groupId)
        if (!group) {
            response.sendError(404)
            return
        }

        def user = Person.get(authenticateService.principal().domainClass.id)

        def groupRequest = new GroupRequest(requester: user, group: group, requestersReason: params.reason).save(flush: true)

        // TODO message user, admin

        redirect(action: 'show', id: group.slug)
    }

    def listRequests = {
        def group = Group.findBySlug(params.id)
        if (!group) {
            response.sendError(404)
            return
        }

        if (!permissionsService.groups.userIsMember(group)) {
            response.sendError(403)
            return
        }

        def list = GroupRequest.withCriteria {
            group {
                eq('id', group.id)
            }
        }

        [requestList: list]
    }

    def showRequest = {
        def groupRequest = GroupRequest.get(params.id)
        if (!groupRequest) {
            response.sendError(404)
            return
        }

        if (!(permissionsService.groups.userIsMember(groupRequest.group) 
            || authenticateService.principal().domainClass.id == groupRequest.requester.id)) {
            response.sendError(403)
            return
        }

        [request: groupRequest]
    }

    def approveRequest = {
        def groupRequest = GroupRequest.get(params.id)
        if (!groupRequest) {    
            response.sendError(404)
            return
        }

        if (!permissionsService.groups.userIsAdmin(groupRequest.group)) {
            response.sendError(403)
            return
        }

        groupRequest.group.addToMembers(groupRequest.requester).save()
        def id = groupRequest.group.slug
        groupRequest.delete()

        // TODO message user, admin

        redirect(action: "listRequests", id: id)
    }

    def denyRequest = {
        def groupRequest = GroupRequest.get(params.id)
        if (!groupRequest) {
            response.sendError(404)
            return
        }

        if (!permissionsService.groups.userIsAdmin(groupRequest.group)) {
            response.sendError(403)
            return
        }

        groupRequest.adminsReason = params.reason
        groupRequest.save(flush: true)

        // TODO message user, admin

        redirect(action: "listRequests", id: groupRequest.group.slug)
    }

    def leave = {
        def group = UserGroup.findBySlug(params.id)

        if (!group) {
            response.sendError(404)
            return
        }

        def user = Person.get(authenticateService.principal().domainClass.id)
        if (!(user in group.members)) {
            response.sendError(403) // TODO i18n 
            return
        }

        if (user.id == group.adminId) {
            response.sendError(403) // TODO i18n admin must transfer before leaving
            return
        }

        group.removeFromMembers(user)

        // TODO message user, admin

        redirect(action: "show", id: group.slug)
    }

    def transferAdmin = {
        def group = UserGroup.findBySlug(params.id)
        if (!group) {
            response.sendError(404)
            return
        }

        if (!permissionsService.groups.userIsAdmin(group)) {
            response.sendError(403)
            return
        }

        def newAdmin = Person.findByUsername(params.to)
        if (!newAdmin) {    
            response.sendError(404)
            return
        }

        group.adminId = newAdmin.id

        // TODO message user, admin

        redirect(action: "show", id: group.slug)
    }

    def posts = {
        def group = UserGroup.findBySlug(params.id)
        if (!group) {
            response.sendError(404)
            return
        }

        if (!permissionsService.groups.userCanRead(group)) {
            response.sendError(403)
            return
        }

        def list = listService.listGroupPosts(group, params)
        params.totalPosts = list.totalCount

        [postList: list]
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
        post.addToMembers(owner)

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
            if (UserGroup.findBySlug(params.slug) > 0) {
                // set error on field
            }
            groupInstance = new UserGroup()
            def admin = Person.get(authenticateService.principal().domainClass.id)
            groupInstance.adminId = admin.id
            groupInstance.addToMembers(admin)
        }
        groupInstance.properties = params

        if (groupInstance.save(flush: true)) {
            // TODO message, transact

            redirect(action: 'show', id: groupInstance.slug)
        } else {
            render(view: 'create', model: [instance: groupInstance])
        }
    }

    def delete = {}

}
