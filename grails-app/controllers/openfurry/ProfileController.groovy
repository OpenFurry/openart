package openfurry

class ProfileController {
    def authenticateService

    def fileUploadService

    def imagingService

    static defaultAction = "edit"

    def edit = {
        def person = Person.get(authenticateService.principal().domainClass.id)
        [person: person]
    }

    def save = {
        def user = Person.get(authenticateService.principal().domainClass.id)

        user.properties = params
        if (params.passwd && params.passwd != "") {
            if (params.pass != params.passwd) {
                user.error.rejectValue("passwd", "openfurry.errors.passwordMismatch", "The passwords you entered did not match")
            } else {
                user.passwd = authenticateService.encodePassword(params.passwd)
            }
        }

        def avatar = request.getFile("av")
        if (!avatar.empty) {
            // TODO bill user
            if (avatar.originalFilename.split("\\.")[-1].toLowerCase() in grailsApplication.config.openfurry.fileTypes.image) {
                def dest = new File(fileUploadService.getAvatarDirectory(servletContext.getRealPath("/"), user), "${(new Date()).time}.${avatar.originalFilename.split('\\.')[-1]}")
                imagingService.createThumbnailFile(avatar, dest)
                user.avatar = dest.getCanonicalPath().replaceAll(servletContext.getRealPath("/") + "avatars", '')
            } else {
                user.errors.rejectValue("avatar", "openfurry.errors.fileTypeMismatch", "The uploaded file does not meet the approved file-type requirements")
            }
        }

        if (user.save(flush: true)) {
            // TODO message user

            redirect(controller: "person", action: "show", params: [username: user.username])
        } else {
            render(view: "edit", model: [person: user])
        }
    }

    def props = {
        def user = Person.get(authenticateService.principal().domainClass.id)
        [user: user]
    }

    def addProperty = {
        def prop = new UserProperty()
        prop.properties = params
        prop.user = authenticateService.principal().domainClass
        if (prop.save(flush: true)) {
            // TODO message user
            redirect(action: "props")
        } else {
        }
    }

    def deleteProperty = {
        def prop = UserProperty.get(params.id)
        if (!prop) {
            response.sendError(404) // TODO i18n
            return
        }

        if (!permissionsService.props.userCanDelete(prop)) {
            response.sendError(403)
            return
        }

        prop.delete()

        // TODO message user
        redirec(action: "props")
    }
}
