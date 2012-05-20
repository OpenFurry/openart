package us.jnsq.openfurry

class GroupController {
    static defaultAction = "list"

    def authenticateService

    def permissionsService

    def listService

    def commentService
    
    def messagingService

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

        def user = User.get(authenticateService.principal().domainClass.id)
        user.addToGroups(group).save(flush: true)

        messagingService.transientMessage(
            user,
            grailsApplication.config.openfurry.user.messageTypes.success,
            "openfurry.messages.group.join",
            "Group joined"
        )
        messagingService.persistentMessage(
            User.get(group.adminId),
            grailsApplication.config.openfurry.user.messageTypes.warning,
            "openfurry.messages.group.joined",
            "A new member has joined {0}",
            group.class.toString().split("\\.")[-1],
            group.id
        )

        redirect(action: "show", id: group.slug)
    }

    def requestToJoin = {
        def group = Group.get(params.groupId)
        if (!group) {
            response.sendError(404)
            return
        }

        def user = User.get(authenticateService.principal().domainClass.id)

        if (GroupRequest.findByRequesterAndGroup(user, group).size() > 0) {
            redirect(action: 'show', id: group.slug)
        }

        def groupRequest = new GroupRequest(requester: user, group: group, requestersReason: params.reason).save(flush: true)

        messagingService.transientMessage(
            user,
            grailsApplication.config.openfurry.user.messageTypes.success,
            "openfurry.messages.group.request",
            "Request posted"
        )
        messagingService.persistentMessage(
            User.get(group.adminId),
            grailsApplication.config.openfurry.user.messageTypes.warning,
            "openfurry.messages.group.newRequest",
            "You have received a new request to join {0}",
            group.class.toString().split("\\.")[-1],
            group.id
        )

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

        messagingService.transientMessage(
            User.get(authenticateService.principal().domainClass.id),
            grailsApplication.config.openfurry.user.messageTypes.success,
            "openfurry.messages.group.approveRequest",
            "Request approved"
        )
        messagingService.persistentMessage(
            groupRequest.requester,
            grailsApplicaton.config.openfurry.user.messageTypes.success,
            "openfurry.messages.group.requestApproved",
            "Your request to join {0} has been approved",
            groupRequest.group.class.toString().split("\\.")[-1],
            groupRequest.group.id
        )

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
        messagingService.transientMessage(
            User.get(authenticateService.principal().domainClass.id),
            grailsApplication.config.openfurry.user.messageTypes.success,
            "openfurry.messages.group.denyRequest",
            "Request denied"
        )
        messagingService.persistentMessage(
            groupRequest.requester,
            grailsApplicaton.config.openfurry.user.messageTypes.failure,
            "openfurry.messages.group.requestDenied",
            "Your request to join {0} has been denied",
            groupRequest.group.class.toString().split("\\.")[-1],
            groupRequest.group.id
        )

        redirect(action: "listRequests", id: groupRequest.group.slug)
    }

    def leave = {
        def group = UserGroup.findBySlug(params.id)

        if (!group) {
            response.sendError(404)
            return
        }

        def user = User.get(authenticateService.principal().domainClass.id)
        if (!(user in group.members)) {
            response.sendError(403) // TODO i18n 
            return
        }

        if (user.id == group.adminId) {
            response.sendError(403) // TODO i18n admin must transfer before leaving
            return
        }

        group.removeFromMembers(user)

        messagingService.transientMessage(
            user,
            grailsApplication.config.openfurry.user.messageTypes.success,
            "openfurry.messages.group.leave",
            "Group left"
        )

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

        def oldAdmin = User.get(group.adminId)
        def newAdmin = User.findByUsername(params.to)
        if (!newAdmin) {    
            response.sendError(404)
            return
        }

        group.adminId = newAdmin.id

        messagingService.transientMessage(
            oldAdmin,
            grailsApplication.config.openfurry.user.messageTypes.success,
            "openfurry.messages.group.adminTransfered",
            "Admin status transfered"
        )
        messagingService.persistentMessage(
            newAdmin,
            grailsApplication.config.openfurry.user.messageTypes.success,
            "openfurry.messages.group.adminReceived",
            "The admin of {0} has transfered his admin status to you, rule wisely!"
        )

        redirect(action: "show", id: group.slug)
    }

    def threads = {
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

        [group: group, postList: list]
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
            // TODO transact
            messagingService.transientMessage(
                owner,
                grailsApplicaton.config.openfurry.user.messageTypes.success,
                "openfurry.messages.group.post",
                "Thread posted"
            )
            group.members.each {
                messagingService.persistentMessage (
                    it,
                    grailsApplication.config.openfurry.user.messageTypes.success,
                    "openfurry.messages.group.newPost",
                    "A new thread has been posted in {0} by {1}",
                    group.class.toString().split("\\.")[-1],
                    group.id,
                    owner
                )
            }

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
        def owner = post.owner
        commentService.deleteCommentsForObject(post)
        post.delete()

        //TODO transact
        messagingService.transientMessage(
            owner,
            grailsApplication.config.openfurry.user.messageTypes.success,
            "openfurry.messages.group.deletePost",
            "Thread deleted"
        )

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
                groupInstance.errors.rejectValue("slug", "openfurry.technical.slugUnique", "Slug field must be unique")
            }
            groupInstance = new UserGroup()
            def admin = User.get(authenticateService.principal().domainClass.id)
            groupInstance.adminId = admin.id
            groupInstance.addToMembers(admin)
        }
        groupInstance.properties = params

        if (groupInstance.save(flush: true)) {
            // TODO transact
            messagingService.transientMessage(
                User.get(groupInstance.adminId),
                grailsApplication.config.openfurry.user.messageTypes.success,
                "openfurry.messages.group.save",
                "Group saved"
            )

            redirect(action: 'show', id: groupInstance.slug)
        } else {
            render(view: 'create', model: [instance: groupInstance])
        }
    }

    def delete = {
        def group = UserGroup.get(params.id)

        if (!group) {
            response.sendError(404)
            return
        }

        if (!permissionsService.groups.userIsAdmin(group)) {
            response.sendError(403)
            return
        }
        
        group.posts.each {
            commentService.deleteCommentsForObject(it)
            it.delete()
        }
        def members = group.members
        group.delete()

        // TODO charge user
        messagingService.transientMessage(
            User.get(authenticateService.principal().domainClass.id),
            grailsApplication.config.openfurry.user.messageTypes.success,
            "openfurry.messages.group.delete",
            "Group deleted"
        )
        members.each {
            messagingService.persistentMessage(
                it,
                grailsApplication.config.openfurry.user.messageTypes.failure,
                "openfurry.messages.group.deleted",
                "{0} has been deleted by the admin"
            )
        }

        redirect(action: "list")
    }

}
