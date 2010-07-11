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

    // Needed for tagging
    def tagService

    def index = { }

    def chooseType = {
        redirect(action: params.type ?: "index")
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
    def orderedCollection = {
        def orderedCollectionInstance = new OrderedCollection()
        orderedCollectionInstance.properties = params
        return [orderedCollectionInstance: orderedCollectionInstance]
    }
    def unorderedCollection = {
        def unorderedCollectionInstance = new Collection()
        unorderedCollectionInstance.properties = params
        return [unorderedCollectionInstance: unorderedCollectionInstance]
    }

    /*
     * Save views
     */
    def saveAudio = {
        def audioUserObjectInstance = new AudioUserObject(params)
        def owner = Person.findByUsername(authenticateService.principal().username)
        audioUserObjectInstance.owner = owner

        Long time = new Date().getTime()
        
        // Handle uploaded file
        def uploadedFile = request.getFile('file')
        if (!uploadedFile.empty) {
            if (uploadedFile.originalFilename.split("\\.")[-1].toLowerCase() in grailsApplication.config.openfurry.fileTypes.audio) {
                def dest = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "audio"), "${time}.${owner.username}_${uploadedFile.originalFilename}")
                uploadedFile.transferTo(dest)
                audioUserObjectInstance.file = dest.getCanonicalPath().replaceAll(servletContext.getRealPath("/"), '')
            } else {
                audioUserObjectInstance.errors.rejectValue("file", "openfurry.errors.fileTypeMismatch", "The uploaded file does not meet the approved file-type requirements")
            }
        } else {
            audioUserObjectInstance.file = null
        }

        def thumbnail = request.getFile('thumbnail')
        if (!thumbnail.empty) {
            if (thumbnail.originalFilename.split("\\.")[-1].toLowerCase() in grailsApplication.config.openfurry.fileTypes.image) {
                def dest = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "thumbs"), "${time}.${owner.username}_${thumbnail.originalFilename}")
                imagingService.createThumbnailFile(thumbnail, dest)
                audioUserObjectInstance.thumbnail = dest.getCanonicalPath().replaceAll(servletContext.getRealPath("/"), '')
            } else {
                audioUserObjectInstance.errors.rejectValue("thumbnail", "openfurry.errors.fileTypeMismatch", "The uploaded file does not meet the approved file-type requirements")
            }
        } else {
            audioUserObjectInstance.thumbnail = grailsApplication.config.openfurry.defaultIcons['audio']
        }

        if (audioUserObjectInstance.save(flush: true)) {
            // Pay the user for their upload
            marketService.transact(owner, "AudioUserObject.create(memberClass:${owner.memberClass})")

            // Tag the object
            tagService.tagObject(audioUserObjectInstance, params.tagString)

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
            if (uploadedFile.originalFilename.split("\\.")[-1].toLowerCase() in grailsApplication.config.openfurry.fileTypes.video) {
                def dest = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "video"), "${time}.${owner.username}_${uploadedFile.originalFilename}")
                uploadedFile.transferTo(dest)
                videoUserObjectInstance.file = dest.getCanonicalPath().replaceAll(servletContext.getRealPath("/"), '')
            } else {
                videoUserObjectInstance.errors.rejectValue("file", "openfurry.errors.fileTypeMismatch", "The uploaded file does not meet the approved file-type requirements")
            }
        } else { 
            videoUserObjectInstance.file = null
        }

        def thumbnail = request.getFile('thumbnail')
        if (!thumbnail.empty) {
            if (thumbnail.originalFilename.split("\\.")[-1].toLowerCase() in grailsApplication.config.openfurry.fileTypes.image) {
                def dest = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "thumbs"), "${time}.${owner.username}_${thumbnail.originalFilename}")
                imagingService.createThumbnailFile(thumbnail, dest)
                videoUserObjectInstance.thumbnail = dest.getCanonicalPath().replaceAll(servletContext.getRealPath("/"), '')
            } else {
                videoUserObjectInstance.errors.rejectValue("thumbnail", "openfurry.errors.fileTypeMismatch", "The uploaded file does not meet the approved file-type requirements")
            }
        } else {
            videoUserObjectInstance.thumbnail = grailsApplication.config.openfurry.defaultIcons['video']
        }

        if (videoUserObjectInstance.save(flush: true)) {
            marketService.transact(owner, "VideoUserObject.create(memberClass:${owner.memberClass})")

            // Tag the object
            tagService.tagObject(videoUserObjectInstance, params.tagString)

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
            if (uploadedFile.originalFilename.split("\\.")[-1].toLowerCase() in grailsApplication.config.openfurry.fileTypes.flash) {
                def dest = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "flash"), "${time}.${owner.username}_${uploadedFile.originalFilename}")
                uploadedFile.transferTo(dest)
                flashUserObjectInstance.file = dest.getCanonicalPath().replaceAll(servletContext.getRealPath("/"), '')
            } else {
                flashUserObjectInstance.errors.rejectValue("file", "openfurry.errors.fileTypeMismatch", "The uploaded file does not meet the approved file-type requirements")
            }
        } else {
            flashUserObjectInstance.file = null
        }

        def thumbnail = request.getFile('thumbnail')
        if (!thumbnail.empty) {
            if (thumbnail.originalFilename.split("\\.")[-1].toLowerCase() in grailsApplication.config.openfurry.fileTypes.image) {
                def dest = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "thumbs"), "${time}.${owner.username}_${thumbnail.originalFilename}")
                imagingService.createThumbnailFile(thumbnail, dest)
                flashUserObjectInstance.thumbnail = dest.getCanonicalPath().replaceAll(servletContext.getRealPath("/"), '')
            } else {
                flashUserObjectInstance.errors.rejectValue("thumbnail", "openfurry.errors.fileTypeMismatch", "The uploaded file does not meet the approved file-type requirements")
            }
        } else {
            flashUserObjectInstance.thumbnail = grailsApplication.config.openfurry.defaultIcons['flash']
        }

        if (flashUserObjectInstance.save(flush: true)) {
            marketService.transact(owner, "FlashUserObject.create(memberClass:${owner.memberClass})")

            // Tag the object
            tagService.tagObject(flashUserObjectInstance, params.tagString)

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
        def imageUserObjectInstance = new ImageUserObject(params)
        def owner = Person.findByUsername(authenticateService.principal().username)
        imageUserObjectInstance.owner = owner

        def time = new Date().getTime()
        def files

        // Handle uploaded file
        def uploadedFile = request.getFile('file')
        if (!uploadedFile.empty) {
            if (uploadedFile.originalFilename.split("\\.")[-1].toLowerCase() in grailsApplication.config.openfurry.fileTypes.image) {
                def dest = fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "image")
                files = imagingService.createImageUserObjectFiles(owner, request.getFile('file'), dest, time)
                imageUserObjectInstance.thumbnail = files[2].getCanonicalPath().replaceAll(servletContext.getRealPath("/"), '')
                imageUserObjectInstance.sizedFile = files[1].getCanonicalPath().replaceAll(servletContext.getRealPath("/"), '')
                imageUserObjectInstance.fullFile = files[0].getCanonicalPath().replaceAll(servletContext.getRealPath("/"), '')
            } else {
                imageUserObjectInstance.errors.rejectValue("file", "openfurry.errors.fileTypeMismatch", "The uploaded file does not meet the approved file-type requirements")
            }
        } else {
            imageUserObjectInstance.fullFile = null
        }

        def thumbnail = request.getFile('thumbnail')
        if (!thumbnail.empty) {
            if (thumbnail.originalFilename.split("\\.")[-1].toLowerCase() in grailsApplication.config.openfurry.fileTypes.image) {
                files[2].delete()
                def dest = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "thumbs"), "${time}.${owner.username}_${thumbnail.originalFilename}")
                imagingService.createThumbnailFile(thumbnail, dest)
                imageUserObjectInstance.thumbnail = dest.getCanonicalPath().replaceAll(servletContext.getRealPath("/"), '')
            } else {
                imageUserObjectInstance.errors.rejectValue("thumbnail", "openfurry.errors.fileTypeMismatch", "The uploaded file does not meet the approved file-type requirements")
            }
        }

        if (imageUserObjectInstance.save(flush: true)) {
            // Pay the user for their upload
            marketService.transact(owner, "ImageUserObject.create(memberClass:${owner.memberClass})")

            // Tag the object
            tagService.tagObject(imageUserObjectInstance, params.tagString)

            // Inform the user
            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'imageUserObject.label', default: 'Image submission'), params.id])}"

            // Redirect to view the upload
            redirect(controller: "view", action: "image", id: imageUserObjectInstance.id)
        } else {
            // Make sure to delete the files
            files.each { it.delete() }

            // Render back with errors
            render(view: "image", model: [instance: imageUserObjectInstance])
        }
    }
    def saveText = {
        def textUserObjectInstance = new TextUserObject(params)
        def owner = Person.findByUsername(authenticateService.principal().username)
        textUserObjectInstance.owner = owner
        textUserObjectInstance.journal = false

        def uploadedFile = request.getFile('attachment')
        if (!uploadedFile.empty) {
            if (uploadedFile.originalFilename.split("\\.")[-1].toLowerCase() in grailsApplication.config.openfurry.fileTypes.text) {
                def dest = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "text"), "${time}.${owner.username}_${uploadedFile.originalFilename}")
                uploadedFile.transferTo(dest)
                textUserObjectInstance.file = dest.getCanonicalPath().replaceAll(servletContext.getRealPath("/"), '')
            } else {
                textUserObjectInstance.errors.rejectValue("attachment", "openfurry.errors.fileTypeMismatch", "The uploaded file does not meet the approved file-type requirements")
            }
        } else {
            textUserObjectInstance.attachment = null
        }

        def thumbnail = request.getFile('thumbnail')
        if (!thumbnail.empty) {
            if (thumbnail.originalFilename.split("\\.")[-1].toLowerCase() in grailsApplication.config.openfurry.fileTypes.image) {
                def dest = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "thumbs"), "${time}.${owner.username}_${thumbnail.originalFilename}")
                imagingService.createThumbnailFile(thumbnail, dest)
                textUserObjectInstance.thumbnail = dest.getCanonicalPath().replaceAll(servletContext.getRealPath("/"), '')
            } else {
                textUserObjectInstance.errors.rejectValue("thumbnail", "openfurry.errors.fileTypeMismatch", "The uploaded file does not meet the approved file-type requirements")
            }
        } else {
            textUserObjectInstance.thumbnail = grailsApplication.config.openfurry.defaultIcons['text']
        }

        if (textUserObjectInstance.save(flush: true)) {
            marketService.transact(owner, "TextUserObject.create(memberClass:${owner.memberClass})")

            // Tag the object
            tagService.tagObject(textUserObjectInstance, params.tagString)

            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'textUserObject.label', default: 'Text submission'), params.id])}"
            redirect(controller: "view", action: "text", id: textUserObjectInstance.id)
        } else {
            if (textUserObjectInstance.attachment != null) {
                def f = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "text"), uploadedFile.originalFilename)
                f.delete()
            }
            render(view: "text", model: [instance: textUserObjectInstance])
        }
    }
    def saveJournal = {
        def textUserObjectInstance = new TextUserObject(params)
        def owner = Person.findByUsername(authenticateService.principal().username)
        textUserObjectInstance.owner = owner
        textUserObjectInstance.journal = true
        textUserObjectInstance.attachment = null
        if (textUserObjectInstance.save(flush: true)) {
            marketService.transact(owner, "Journal.create(memberClass:${owner.memberClass})")

            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'textUserObject.label', default: 'Journal'), params.id])}"
            redirect(controller: "view", action: "text", id: textUserObjectInstance.id)
        } else {
            render(view: "journal", model: [instance: textUserObjectInstance])
        }
    }
    def saveApplication = {
        def applicationUserObjectInstance = new ApplicationUserObject(params)
        def owner = Person.findByUsername(authenticateService.principal().username)
        applicationUserObjectInstance.owner = owner

        def uploadedFile = request.getFile("screenshot")
        if (!uploadedFile.empty) {
            if (uploadedFile.originalFilename.split("\\.")[-1].toLowerCase() in grailsApplication.config.openfurry.fileTypes.pplication) {
                def dest = new File(fileUploadService.getSubmissionDirectory(servletConapplication.getRealPath("/"), owner, "application"), "${time}.${owner.username}_${uploadedFile.originalFilename}")
                uploadedFile.transferTo(dest)
                applicationUserObjectInstance.file = dest.getCanonicalPath().replaceAll(servletConapplication.getRealPath("/"), '')
            } else {
                applicationUserObjectInstance.errors.rejectValue("screenshot", "openfurry.errors.fileTypeMismatch", "The uploaded file does not meet the approved file-type requirements")
            }
        } else {
            applicationUserObjectInstance.screenshot = null
        }

        def thumbnail = request.getFile('thumbnail')
        if (!thumbnail.empty) {
            if (thumbnail.originalFilename.split("\\.")[-1].toLowerCase() in grailsApplication.config.openfurry.fileTypes.image) {
                def dest = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "thumbs"), "${time}.${owner.username}_${thumbnail.originalFilename}")
                imagingService.createThumbnailFile(thumbnail, dest)
                applicationUserObjectInstance.thumbnail = dest.getCanonicalPath().replaceAll(servletContext.getRealPath("/"), '')
            } else {
                applicationUserObjectInstance.errors.rejectValue("thumbnail", "openfurry.errors.fileTypeMismatch", "The uploaded file does not meet the approved file-type requirements")
            }
        } else {
            applicationUserObjectInstance.thumbnail = grailsApplication.config.openfurry.defaultIcons['application']
        }

        if (applicationUserObjectInstance.save(flush: true)) {
            marketService.transact(owner, "ApplicationUserObject.create(memberClass:${owner.memberClass})")

            // Tag the object
            tagService.tagObject(applicationUserObjectInstance, params.tagString)

            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'applicationUserObject.label', default: 'Application submission'), params.id])}"
            redirect(controller: "view", action: "application", id: applicationUserObjectInstance.id)
        } else {
            render(view: "application", model: [instance: applicationUserObjectInstance])
        }
    }
    def saveOrderedCollection = {
        def orderedCollectionInstance = new OrderedCollection(params)
        def owner = Person.findByUsername(authenticateService.principal().username)
        orderedCollectionInstance.owner = owner

        if (orderedCollectionInstance.save(flush: true)) {
            marketService.transact(owner, "OrderedCollection.create(memberClass:${owner.memberClass})")

            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'orderedCollection.label', default: 'Ordered collection'), params.id])}"
            redirect(controller: "view", action:"collection", id: orderedCollectionInstance.id)
        } else {
            render(view: "orderedCollection", model: [instance: orderedCollectionInstance])
        }
        
    }
    def saveUnorderedCollection = {
        def unorderedCollectionInstance = new UnorderedCollection(params)
        def owner = Person.findByUsername(authenticateService.principal().username)
        unorderedCollectionInstance.owner = owner

        if (unorderedCollectionInstance.save(flush: true)) {
            marketService.transact(owner, "UnorderedCollection.create(memberClass:${owner.memberClass})")

            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'unorderedCollection.label', default: 'Unordered collection'), params.id])}"
            redirect(controller: "view", action:"collection", id: unorderedCollectionInstance.id)
        } else {
            render(view: "unorderedCollection", model: [instance: unorderedCollectionInstance])
        }
    }
}
