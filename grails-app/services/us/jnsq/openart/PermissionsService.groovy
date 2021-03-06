package us.jnsq.openart

class PermissionsService {

    static transactional = true

    def authenticateService

    def uo = [
        userCanView: { uo ->
            if (uo.published) {
                if (uo.takenDown) {
                    if (authenticateService.principal().domainClass.id == uo.owner.id
                        || authenticateService.ifAnyGranted("ROLE_STAFF,ROLE_ADMIN")) {
                        return true
                    } else {
                        return false
                    }
                } else {
                    if (uo.friendsOnly) {
                        if (authenticateService.principal().domainClass.id == uo.owner.id
                            || authenticateService.principal().domainClass in uo.owner.friends
                            || authenticateService.ifAnyGranted("ROLE_STAFF,ROLE_ADMIN")) {
                            return true
                        } else {
                            return false
                        }
                    } else {
                        return true
                    }
                }
            } else {
                if (authenticateService.principal().domainClass.id == uo.owner.id
                    || authenticateService.ifAnyGranted("ROLE_STAFF,ROLE_ADMIN")) {
                    return true
                } else {
                    return false
                }
            }
        },
        userViewCounts: { uo ->
            if (authenticateService.isLoggedIn()) {
                if (authenticateService.principal().domainClass.id == uo.owner.id) {
                    return false
                } else {
                    return true
                }
            } else {
                return false
            }
        },
        userCanEdit: { uo ->
            if (authenticateService.principal().domainClass.id == uo.owner.id
                || authenticateService.ifAnyGranted("ROLE_STAFF,ROLE_ADMIN")) {
                return true
            } else {
                return false
            }
        }
    ]

    def comments = [
        userCanDelete: { comment ->
            if (authenticateService.principal().domainClass.id == comment.owner.id
                || authenticateService.ifAnyGranted("ROLE_STAFF,ROLE_ADMIN")) {
                return true
            }
            switch (comment.parentType) {
                case "UserObject":
                case "AudioUserObject":
                case "VideoUserObject":
                case "FlashUserObject":
                case "ImageUserObject":
                case "TextUserObject":
                case "ApplicationUserObject":
                case "OrderedCollection":
                case "UnorderedCollection":
                    def obj = UserObject.get(comment.parentId)
                    if (authenticateService.principal().domainClass.id == obj.owner.id) {
                        return true
                    }
                    break
                case "GroupPost":
                    def obj = GroupPost.get(comment.parentId)
                    if(authenticateService.principal().domainClass.id == obj.group.adminId) {
                        return true
                    }
            }
            return false
        }
    ]

    def market = [
        userCanAfford: { pennies ->
            if (authenticateService.principal().domainClass.pennies - pennies < 0) {
                return false
            }
            return true
        }
    ]

    def groups = [
        userIsMember: { group ->
            return (authenticateService.principal().domainClass in group.members)
        },
        userIsAdmin: { group ->
            return (authenticateService.principal().domainClass.id == group.adminId
                || authenticateService.ifAnyGranted("ROLE_ADMIN,ROLE_STAFF"))
        },
        userCanRead: { group ->
            if (group.exclusive) {
                if (authenticateService.principal().domainClass in group.members
                    || authenticateService.principal().domainClass.id == group.adminId
                    || authenticateService.ifAnyGranted("ROLE_ADMIN")) {
                    return true
                }
            } else {
                return true
            }
            return false
        },
        userCanPost: { group ->
            if (authenticateService.principal().domainClass in group.members
                || authenticateService.principal().domainClass.id == group.adminId) {
                return true
            }
            return false
        },
        userCanDeletePost: { post ->
            if (authenticateService.principal().domainClass == post.owner 
                || authenticateService.principal().domainClass.id == post.group.adminId 
                || authenticateService.ifAnyGranted("ROLE_ADMIN,ROLE_STAFF")) {
                return true
            }
            return false
        },
        userCanEditGroup: { group ->
            if (authenticateService.principal().domainClass.id == group.adminId
                || authenticateService.ifAnyGranted("ROLE_ADMIN,ROLE_STAFF")) {
                return true
            }
            return false
        }
    ]

    def troubleTickets = [
        userCanView: { ticket ->
            if (authenticateService.principal().domainClass == ticket.submitter
                || authenticateService.ifAnyGranted("ROLE_ADMIN,ROLE_STAFF")) {
                return true
            }
            return false
        }
    ]

    def props = [
        userCanDelete: { prop ->
            if (prop.owner == authenticateService.principal().domainClass
                || authenticateService.ifAnyGranted("ROLE_ADMIN,ROLE_STAFF")) {
                return true
            }
            return false
        }
    ]
}
