package openfurry

class PermissionsService {

    static transactional = true

    def authenticateService

    def groups = [
        userCanRead: { group ->
            if (group.exclusive) {
                if (!(authenticateService.principal().domainClass in group.members)) {
                    return false
                }
            }
            return true
        },
        userCanPost: { group ->
            if (authenticateService.principal().domainClass in group.members
                || authenticateService.principal().domainClass.id == group.admin.id) {
                return true
            }
            return false
        },
        userCanDeletePost: { post ->
            if (authenticateService.principal().domainClass == post.owner ||
                authenticateService.principal().domainClass == post.group.admin ||
                authenticateService.ifAnyGranted("ROLE_ADMIN,ROLE_STAFF")) {
                return true
            }
            return false
        },
        userCanEditGroup: { group ->
            if (authenticateService.principal().domainClass == group.admin ||
                authenticateService.ifAnyGranted("ROLE_ADMIN,ROLE_STAFF")) {
                return true
            }
            return false
        }
            
    ]
}
