package us.jnsq.openart

class ViewController {

    def permissionsService

    def show = {
        def userObject = UserObject.get(params.id)
        
        if (userObject && permissionsService.uo.userCanView(userObject)) {
            if (permissionsService.uo.userViewCounts(userObject)) {
                userObject.views++
                userObject.save(flush: true)
            }
            render(view: userObject.type, model: [instance: userObject])
        } else {
            response.sendError(404) // TODO i18n
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
    def orderedCollection = {
        def orderedCollectionInstance = OrderedCollection.get(params.id)
        if (!orderedCollectionInstance) {
            response.sendError 404
        } else {
            [instance: orderedCollectionInstance]
        }
    }
    def unorderedCollection = {
        def unorderedCollectionInstance = UnorderedCollection.get(params.id)
        if (!unorderedCollectionInstance) {
            response.sendError 404
        } else {
            [instance: unorderedCollectionInstance]
        }
    }
}
