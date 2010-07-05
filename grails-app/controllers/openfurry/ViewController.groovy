package openfurry

class ViewController {
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
