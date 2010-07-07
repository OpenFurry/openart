package openfurry

class ViewController {

    def show = {
        def userObject = UserObject.get(params.id)
        
        switch (userObject) {
            case AudioUserObject:
                redirect(action: 'audio', id: userObject.id)
                break
            case VideoUserObject:
                redirect(action: 'video', id: userObject.id)
                break
            case FlashUserObject:
                redirect(action: 'flash', id: userObject.id)
                break
            case ImageUserObject:
                redirect(action: 'image', id: userObject.id)
                break
            case TextUserObject:
                redirect(action: 'text', id: userObject.id)
                break
            case ApplicationUserObject:
                redirect(action: 'application', id: userObject.id)
                break
            default:
                response.sendError(404)
        }
    }

    def audio = {
        def audioUserObjectInstance = AudioUserObject.get(params.id)
        if (!audioUserObjectInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'audioUserObject.label', default: 'AudioUserObject'), params.id])}"
            response.status = 404
        } else {
            [audioUserObjectInstance: audioUserObjectInstance]
        }
    }
}
