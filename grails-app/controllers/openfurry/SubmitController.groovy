package openfurry

class SubmitController {

    // Needed for current user
    def authenticateService

    // Needed for uploaded files
    def fileUploadService

    // Needed for reimbursement for posting
    def marketService

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
        def owner = Person.findByUsername(authenticateService.principal().username)
        audioUserObjectInstance.owner = owner
        
        // Handle uploaded file
        def uploadedFile = request.getFile('file')
        uploadedFile.transferTo(new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "audio"), uploadedFile.originalFilename))
        audioUserObjectInstance.file = uploadedFile.originalFilename

        if (audioUserObjectInstance.save(flush: true)) {
            // Pay the user for their upload
            marketService.transact(owner, "AudioUserObject", "userclass:${owner.userClass}")

            // Inform them of the upload
            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'audioUserObject.label', default: 'Audio submission'), params.id])}"

            // Redirect to view the upload
            redirect(controller: "view", action: "audio", id: audioUserObjectInstance.id)
        } else {
            // Make sure to remove the file
            def f = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "audio"), uploadedFile.originalFilename)
            f.delete()

            // Redirect back with errors
            render(view: "createAudio", model: [instance: audioUserObjectInstance])
        }
    }
    def saveVideo = {
        def videoUserObjectInstance = new VideoUserObject(params)
        def owner = Person.findByUsername(authenticateService.principal().username)
        videoUserObjectInstance.owner = owner
        
        // Handle uploaded file
        def uploadedFile = request.getFile('file')
        uploadedFile.transferTo(new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "video"), uploadedFile.originalFilename))
        videoUserObjectInstance.file = uploadedFile.originalFilename

        if (videoUserObjectInstance.save(flush: true)) {
            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'videoUserObject.label', default: 'Video submission'), params.id])}"
            redirect(controller: "view", action: "video", id: videoUserObjectInstance.id)
        } else {
            def f = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "video"), uploadedFile.originalFilename)
            f.delete()
            render(view: "createVideo", model: [instance: videoUserObjectInstance])
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
            render(view: "createAudio", model: [instance: flashUserObjectInstance])
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
            render(view: "createAudio", model: [instance: imageUserObjectInstance])
        }
    }
    def saveText = {
        def textUserObjectInstance = new TextUserObject(params)
        textUserObjectInstance.owner = authenticateService.principal()
        textUserObjectInstance.journal = false
        if (textUserObjectInstance.save(flush: true)) {
            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'textUserObject.label', default: 'Text submission'), params.id])}"
            redirect(controller: "view", action: "text", id: textUserObjectInstance.id)
        } else {
            render(view: "createAudio", model: [instance: textUserObjectInstance])
        }
    }
    def saveJournal = {
        def textUserObjectInstance = new TextUserObject(params)
        textUserObjectInstance.owner = authenticateService.principal()
        textUserObjectInstance.journal = true
        if (textUserObjectInstance.save(flush: true)) {
            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'textUserObject.label', default: 'Journal'), params.id])}"
            redirect(controller: "view", action: "text", id: textUserObjectInstance.id)
        } else {
            render(view: "createAudio", model: [instance: textUserObjectInstance])
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
            render(view: "createAudio", model: [instance: applicationUserObjectInstance])
        }
    }
}
