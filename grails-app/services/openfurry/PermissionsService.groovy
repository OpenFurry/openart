package openfurry

class PermissionsService {

    static transactional = true

    def authenticateService

    def groups = [
        userIsMember: { group ->
            return (authenticateService.principal().domainClass in group.members)
        },
        userIsAdmin: { group ->
            return authenticateService.principal().domainClass.id == group.adminId
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
}
