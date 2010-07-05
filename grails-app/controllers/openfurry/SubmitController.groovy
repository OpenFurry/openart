package openfurry

class SubmitController {

    // Needed for current user
    def authenticateService

    def index = { }

    def chooseType = {
        redirect(action: params.type)
    }

    /*
     * Create views
     */
    def createAudio = {
        def audioUserObjectInstance = new AudioUserObject()
        audioUserObjectInstance.properties = params
        return [audioUserObjectInstance: audioUserObjectInstance]
    }
    def createVideo = {
        def videoUserObjectInstance = new VideoUserObject()
        videoUserObjectInstance.properties = params
        return [videoUserObjectInstance: videoUserObjectInstance]
    }
    def createFlash = {
        def flashUserObjectInstance = new FlashUserObject()
        flashUserObjectInstance.properties = params
        return [flashUserObjectInstance: flashUserObjectInstance]
    }
    def createImage = {
        def imageUserObjectInstance = new ImageUserObject()
        imageUserObjectInstance.properties = params
        return [imageUserObjectInstance: imageUserObjectInstance]
    }
    def createText = {
        def textUserObjectInstance = new TextUserObject()
        textUserObjectInstance.properties = params
        return [textUserObjectInstance: textUserObjectInstance]
    }
    def createJournal = {
        def textUserObjectInstance = new TextUserObject()
        textUserObjectInstance.properties = params
        return [textUserObjectInstance: textUserObjectInstance]
    }
    def createApplication = {
        def applicationUserObjectInstance = new ApplicationUserObject()
        applicationUserObjectInstance.properties = params
        return [applicationUserObjectInstance: applicationUserObjectInstance]
    }

    /*
     * Save views
     */
    def saveAudio = {
        def audioUserObjectInstance = new AudioUserObject(params)
        audioUserObjectInstance.owner = authenticateService.principal()
        if (audioUserObjectInstance.save(flush: true)) {
            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'audioUserObject.label', default: 'Audio submission'), params.id])}"
            redirect(controller: "view", action: "audio", id: audioUserObjectInstance.id)
        } else {
            render(view: "createAudio", model: [audioUserObjectInstance: audioUserObjectInstance])
        }
    }
    def saveVideo = {
        def videoUserObjectInstance = new VideoUserObject(params)
        videoUserObjectInstance.owner = authenticateService.principal()
        if (videoUserObjectInstance.save(flush: true)) {
            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'videoUserObject.label', default: 'Video submission'), params.id])}"
            redirect(controller: "view", action: "video", id: videoUserObjectInstance.id)
        } else {
            render(view: "createAudio", model: [videoUserObjectInstance: videoUserObjectInstance])
        }
    }
    def saveFlash = {
        def flashUserObjectInstance = new FlashUserObject(params)
        flashUserObjectInstance.owner = authenticateService.principal()
        if (flashUserObjectInstance.save(flush: true)) {
            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'flashUserObject.label', default: 'Flash submission'), params.id])}"
            redirect(controller: "view", action: "flash", id: flashUserObjectInstance.id)
        } else {
            render(view: "createAudio", model: [flashUserObjectInstance: flashUserObjectInstance])
        }
    }
    def saveImage = {
        def imageUserObjectInstance = new FlashUserObject(params)
        imageUserObjectInstance.owner = authenticateService.principal()
        if (imageUserObjectInstance.save(flush: true)) {
            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'imageUserObject.label', default: 'Image submission'), params.id])}"
            redirect(controller: "view", action: "image", id: imageUserObjectInstance.id)
        } else {
            render(view: "createAudio", model: [imageUserObjectInstance: imageUserObjectInstance])
        }
    }
    def saveText = {
        def textUserObjectInstance = new TextUserObject(params)
        textUserObjectInstance.owner = authenticateService.principal()
        textUserObjectInstance.journal = false
        if (textUserObjectInstance.save(flush: true)) {
            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'textUserObject.label', default: 'Text submission'), params.id])}"
            redirect(controller: "view", action: "text", id: audioUserObjectInstance.id)
        } else {
            render(view: "createAudio", model: [textUserObjectInstance: audioUserObjectInstance])
        }
    }
    def saveJournal = {
        def textUserObjectInstance = new TextUserObject(params)
        textUserObjectInstance.owner = authenticateService.principal()
        textUserObjectInstance.journal = true
        if (textUserObjectInstance.save(flush: true)) {
            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'textUserObject.label', default: 'Journal'), params.id])}"
            redirect(controller: "view", action: "text", id: audioUserObjectInstance.id)
        } else {
            render(view: "createAudio", model: [textUserObjectInstance: audioUserObjectInstance])
        }
    }
    def saveApplication = {
        def applicationUserObjectInstance = new ApplicationUserObject(params)
        applicationUserObjectInstance.owner = authenticateService.principal()
        if (applicationUserObjectInstance.save(flush: true)) {
            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'applicationUserObject.label', default: 'Application submission'), params.id])}"
            redirect(controller: "view", action: "application", id: applicationUserObjectInstance.id)
        } else {
            render(view: "createAudio", model: [applicationUserObjectInstance: applicationUserObjectInstance])
        }
    }
}