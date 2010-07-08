package openfurry

class SubmitController {

    // Needed for current user
    def authenticateService

    // Needed for uploaded files
    def fileUploadService

    // Needed for resizing images
    def imagingService

    // Needed for reimbursement for posting
    def marketService

    def index = { }

    def chooseType = {
        redirect(action: params.type)
    }

    /*
     * Create views
     */
    def audio = {
        def audioUserObjectInstance = new AudioUserObject()
        audioUserObjectInstance.properties = params
        return [audioUserObjectInstance: audioUserObjectInstance]
    }
    def video = {
        def videoUserObjectInstance = new VideoUserObject()
        videoUserObjectInstance.properties = params
        return [videoUserObjectInstance: videoUserObjectInstance]
    }
    def flash = {
        def flashUserObjectInstance = new FlashUserObject()
        flashUserObjectInstance.properties = params
        return [flashUserObjectInstance: flashUserObjectInstance]
    }
    def image = {
        def imageUserObjectInstance = new ImageUserObject()
        imageUserObjectInstance.properties = params
        return [imageUserObjectInstance: imageUserObjectInstance]
    }
    def text = {
        def textUserObjectInstance = new TextUserObject()
        textUserObjectInstance.properties = params
        return [textUserObjectInstance: textUserObjectInstance]
    }
    def journal = {
        def textUserObjectInstance = new TextUserObject()
        textUserObjectInstance.properties = params
        return [textUserObjectInstance: textUserObjectInstance]
    }
    def application = {
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
        if (!uploadedFile.empty) {
            uploadedFile.transferTo(new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "audio"), uploadedFile.originalFilename))
            audioUserObjectInstance.file = uploadedFile.originalFilename
        } else {
            audioUserObjectInstance.file = null
        }

        if (audioUserObjectInstance.save(flush: true)) {
            // Pay the user for their upload
            marketService.transact(owner, "AudioUserObject.create(memberClass:${owner.memberClass})")

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
            render(view: "audio", model: [instance: audioUserObjectInstance])
        }
    }
    def saveVideo = {
        def videoUserObjectInstance = new VideoUserObject(params)
        def owner = Person.findByUsername(authenticateService.principal().username)
        videoUserObjectInstance.owner = owner
        
        // Handle uploaded file
        def uploadedFile = request.getFile('file')
        if (!uploadedFile.empty) {
            uploadedFile.transferTo(new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "video"), uploadedFile.originalFilename))
            videoUserObjectInstance.file = uploadedFile.originalFilename
        } else { 
            videoUserObjectInstance.file = null
        }

        if (videoUserObjectInstance.save(flush: true)) {
            marketService.transact(owner, "VideoUserObject.create(memberClass:${owner.memberClass})")

            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'videoUserObject.label', default: 'Video submission'), params.id])}"
            redirect(controller: "view", action: "video", id: videoUserObjectInstance.id)
        } else {
            def f = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "video"), uploadedFile.originalFilename)
            f.delete()
            render(view: "video", model: [instance: videoUserObjectInstance])
        }
    }
    def saveFlash = {
        def flashUserObjectInstance = new FlashUserObject(params)
        def owner = Person.findByUsername(authenticateService.principal().username)
        flashUserObjectInstance.owner = owner
        
        // Handle uploaded file
        def uploadedFile = request.getFile('file')
        if (!uploadedFile.empty) {
            uploadedFile.transferTo(new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "flash"), uploadedFile.originalFilename))
            flashUserObjectInstance.file = uploadedFile.originalFilename
        } else {
            flashUserObjectInstance.file = null
        }

        if (flashUserObjectInstance.save(flush: true)) {
            marketService.transact(owner, "FlashUserObject.create(memberClass:${owner.memberClass})")

            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'flashUserObject.label', default: 'Flash submission'), params.id])}"
            redirect(controller: "view", action: "flash", id: flashUserObjectInstance.id)
        } else {
            def f = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "flash"), uploadedFile.originalFilename)
            f.delete()
            render(view: "flash", model: [instance: flashUserObjectInstance])
        }
    }
    def saveImage = {
        def imageUserObjectInstance = new FlashUserObject(params)
        def owner = Person.findByUsername(authenticateService.principal().username)
        imageUserObjectInstance.owner = owner

        // Handle uploaded file
        def uploadedFile = request.getFile('file')
        if (!uploadedFile.empty) {
            def files = imageUserObjectFiles(owner, request.getFile('file'), fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "image"))
            imageUserObjectInstance.thumbnail = "thumb.${uploadedFile.originalFilename}"
            imageUserObjectInstance.sizedFile = "sized.${uploadedFile.originalFilename}"
            imageUserObjectInstance.fullFile = uploadedFile.originalFilename
        } else {
            imageUserObjectInstance.fullFile = null
        }
        if (imageUserObjectInstance.save(flush: true)) {
            // Pay the user for their upload
            marketService.transact(owner, "ImageUserObject.create(memberClass:${owner.memberClass})")

            // Inform the user
            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'imageUserObject.label', default: 'Image submission'), params.id])}"

            // Redirect to view the upload
            redirect(controller: "view", action: "image", id: imageUserObjectInstance.id)
        } else {
            // Make sure to delete the files
            files.each { it.delete() }

            // Render back with errors
            render(view: "audio", model: [instance: imageUserObjectInstance])
        }
    }
    def saveText = {
        def textUserObjectInstance = new TextUserObject(params)
        textUserObjectInstance.owner = authenticateService.principal()
        textUserObjectInstance.journal = false

        def uploadedFile = request.getFile('attachment')
        if (!uploadedFile.empty) {
            uploadedFile.transferTo(new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "text"), uploadedFile.originalFilename))
            textUserObjectInstance.attachment = uploadedFile.originalFilename
        } else {
            textUserObjectInstance.attachment = null
        }
        if (textUserObjectInstance.save(flush: true)) {
            marketService.transact(owner, "TextUserObject.create(memberClass:${owner.memberClass})")

            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'textUserObject.label', default: 'Text submission'), params.id])}"
            redirect(controller: "view", action: "text", id: textUserObjectInstance.id)
        } else {
            if (textUserObjectInstance.attachment != null) {
                def f = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "text"), uploadedFile.originalFilename)
                f.delete()
            }
            render(view: "audio", model: [instance: textUserObjectInstance])
        }
    }
    def saveJournal = {
        def textUserObjectInstance = new TextUserObject(params)
        textUserObjectInstance.owner = authenticateService.principal()
        textUserObjectInstance.journal = true
        textUserObjectInstance.attachment = null
        if (textUserObjectInstance.save(flush: true)) {
            marketService.transact(owner, "Journal.create(memberClass:${owner.memberClass})")

            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'textUserObject.label', default: 'Journal'), params.id])}"
            redirect(controller: "view", action: "text", id: textUserObjectInstance.id)
        } else {
            render(view: "audio", model: [instance: textUserObjectInstance])
        }
    }
    def saveApplication = {
        def applicationUserObjectInstance = new ApplicationUserObject(params)
        applicationUserObjectInstance.owner = authenticateService.principal()

        def uploadedFile = request.getFile("screenshot")
        if (!uploadedFile.empty) {
            uploadedFile.transferTo(new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "application"), uploadedFile.originalFilename))
            applicationUserObjectInstance.screenshot = uploadedFile.originalFilename
        } else {
            applicationUserObjectInstance.screenshot = null
        }
        if (applicationUserObjectInstance.save(flush: true)) {
            marketService.transact(owner, "ApplicationUserObject.create(memberClass:${owner.memberClass})")

            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'applicationUserObject.label', default: 'Application submission'), params.id])}"
            redirect(controller: "view", action: "application", id: applicationUserObjectInstance.id)
        } else {
            render(view: "audio", model: [instance: applicationUserObjectInstance])
        }
    }
}
