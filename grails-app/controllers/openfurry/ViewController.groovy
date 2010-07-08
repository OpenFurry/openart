package openfurry

class ViewController {

    def show = {
        def userObject = UserObject.get(params.id)
        
        switch (userObject) {
            case AudioUserObject:
                render(view: 'audio', model:[instance: userObject])
                break
            case VideoUserObject:
                render(view: 'video', model:[instance: userObject])
                break
            case FlashUserObject:
                render(view: 'flash', model:[instance: userObject])
                break
            case ImageUserObject:
                render(view: 'image', model:[instance: userObject])
                break
            case TextUserObject:
                if (((TextUserObject) userObject).journal) {
                    redirect(action: 'journal', id: userObject.id)
                } else {
                    render(view: 'text', model:[instance: userObject])
                }
                break
            case ApplicationUserObject:
                render(view: 'application', model:[instance: userObject])
                break
            default:
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'UserObject.label', default: 'UserObject'), params.id])}"
                response.sendError(404, "${message(code: 'default.not.found.message', args: [message(code: 'UserObject.label', default: 'UserObject'), params.id])}")
        }
    }

    def collection = {
        def collection = Collection.get(params.id)
        
        switch (collection) {
            case OrderedCollection:
                render(view: "orderedCollection", model:[instance: collection])
                break
            case UnorderedCollection:
                render(view: "unorderedCollection", model:[instance: collection])
                break
            default:
                response.sendError(404, "${message(code: 'default.not.found.message', args: [message(code: 'Collection.label', default: 'Collection'), params.id])}")
        }
    }

    def audio = {
        def audioUserObjectInstance = AudioUserObject.get(params.id)
        if (!audioUserObjectInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'audioUserObject.label', default: 'AudioUserObject'), params.id])}"
            response.sendError 404
        } else {
            [instance: audioUserObjectInstance]
        }
    }
    def video = {
        def videoUserObjectInstance = VideoUserObject.get(params.id)
        if (!videoUserObjectInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'videoUserObject.label', default: 'VideoUserObject'), params.id])}"
            response.sendError 404
        } else {
            [instance: videoUserObjectInstance]
        }
    }
    def flash = {
        def flashUserObjectInstance = FlashUserObject.get(params.id)
        if (!flashUserObjectInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'flashUserObject.label', default: 'FlashUserObject'), params.id])}"
            response.sendError 404
        } else {
            [instance: flashUserObjectInstance]
        }
    }
    def image = {
        def imageUserObjectInstance = ImageUserObject.get(params.id)
        if (!imageUserObjectInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'imageUserObject.label', default: 'ImageUserObject'), params.id])}"
            response.sendError 404
        } else {
            [instance: imageUserObjectInstance]
        }
    }
    def text = {
        def textUserObjectInstance = TextUserObject.get(params.id)
        if (!textUserObjectInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'textUserObject.label', default: 'TextUserObject'), params.id])}"
            response.sendError 404
        } else {
            [instance: textUserObjectInstance]
        }
    }
    def journal = {
        def journalUserObjectInstance = TextUserObject.get(params.id)
        if (!journalUserObjectInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'journalUserObject.label', default: 'TextUserObject'), params.id])}"
            response.sendError 404
        } else {
            [instance: journalUserObjectInstance]
        }
    }
    def application = {
        def applicationUserObjectInstance = ApplicationUserObject.get(params.id)
        if (!applicationUserObjectInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationUserObject.label', default: 'ApplicationUserObject'), params.id])}"
            response.sendError 404
        } else {
            [instance: applicationUserObjectInstance]
        }
    }
}
