package us.jnsq.openfurry

class SubmitController {

    // Needed for current user
    def authenticateService

    // Needed for uploaded files
    def fileUploadService

    // Needed for resizing images
    def imagingService

    // Needed for thumbnails
    def thumbnailService

    // Needed for reimbursement for posting
    def marketService

    // Needed for tagging
    def tagService

    // Needed for checking permissions
    def permissionsService

    def index = { }

    def chooseType = {
        redirect(action: params.type ?: "index")
    }

    /*
     * Create views
     */
    def audio = {
        if (params.id) {
            params.id = null
        }
        [instance: new AudioUserObject()]
    }
    def video = {
        if (params.id) {
            params.id = null
        }
        [instance: new VideoUserObject()]
    }
    def flash = {
        if (params.id) {
            params.id = null
        }
        [instance: new FlashUserObject()]
    }
    def image = {
        if (params.id) {
            params.id = null
        }
        [instance: new ImageUserObject()]
    }
    def text = {
        if (params.id) {
            params.id = null
        }
        [instance: new TextUserObject()]
    }
    def journal = {
        if (params.id) {
            params.id = null
        }
    }
    def application = {
        if (params.id) {
            params.id = null
        }
        [instance: new ApplicationUserObject()]
    }
    def orderedCollection = {
        if (params.id) {
            params.id = null
        }
        [instance: new OrderedCollection()]
    }
    def unorderedCollection = {
        if (params.id) {
            params.id = null
        }
        [instance: new UnorderedCollection()]
    }

    /*
     * Edit view
     */
    def edit = {
        def uo = UserObject.get(params.id)
        if (!uo || !permissionsService.uo.userCanEdit(uo)) {
            response.sendError(403) //TODO i18n, punish?
            return
        }
        switch (uo) {
            case AudioUserObject:
                def auo = (AudioUserObject) uo
                render(view: "audio", model: [instance: auo])
                break;
            case VideoUserObject:
                def vuo = (VideoUserObject) uo
                render(view: "video", model: [instance: vuo])
                break;
            case FlashUserObject:
                def fuo = (FlashUserObject) uo
                render(view: "flash", model: [instance: fuo])
                break;
            case ImageUserObject:
                def iuo = (ImageUserObject) uo
                render(view: "image", model: [instance: iuo])
                break;
            case TextUserObject:
                def tuo = (TextUserObject) uo
                if (tuo.journal) {
                    render(view: "journal", model: [instance: tuo])
                } else {
                    render(view: "text", model: [instance: tuo])
                }
                break;
            case ApplicationUserObject:
                def appuo = (ApplicationUserObject) uo
                render(view: "application", model: [instance: appuo])
                break;
            case OrderedCollection:
                def oc = (OrderedCollection) uo
                render(view: "orderedCollection", model: [instance: oc])
                break;
            case UnorderedCollection:
                def uc = (UnorderedCollection) uo
                render(view: "unorderedCollection", model: [instance: uc])
                break;
        }
    }

    /*
     * Freezing and unfreezing of comments
     */
    def freezeComments = {
        def uo = UserObject.get(params.id)
        if (uo && permissionsService.uo.userCanEdit(uo)) {
            uo.freezeComments = true
            uo.save(flush: true)
            // TODO message user
            redirect(controller: "view", action: "show", id: uo.id)
        } else {
            response.sendError(404)
            return
        }
    }

    def unFreezeComments = {
        def uo = UserObject.get(params.id)
        if (uo && permissionsService.uo.userCanEdit(uo)) {
            uo.freezeComments = false
            uo.save(flush: true)
            // TODO message user
            redirect(controller: "view", action: "show", id: uo.id)
        } else {
            response.sendError(404)
            return
        }
    }



    /*
     * Save views
     */
    def saveAudio = {
        def audioUserObjectInstance
        if (params.id) {
            audioUserObjectInstance = AudioUserObject.get(params.id)
        } else {
            audioUserObjectInstance = new AudioUserObject()
        }
        audioUserObjectInstance.properties = params
        def owner = User.findByUsername(authenticateService.principal().username)
        audioUserObjectInstance.owner = owner
        audioUserObjectInstance.type = "audio"

        Long time = new Date().getTime()
        
        // Handle uploaded file
        def uploadedFile = request.getFile('audioFile')
        if (!uploadedFile.empty) {
            if (uploadedFile.originalFilename.split("\\.")[-1].toLowerCase() in grailsApplication.config.openfurry.fileTypes.audio) {
                def dest = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "audio"), "${time}.${owner.username}_${uploadedFile.originalFilename}")
                uploadedFile.transferTo(dest)
                audioUserObjectInstance.file = dest.getCanonicalPath().replaceAll(servletContext.getRealPath("/"), '')
            } else {
                audioUserObjectInstance.errors.rejectValue("file", "openfurry.errors.fileTypeMismatch", "The uploaded file does not meet the approved file-type requirements")
            }
        }

        def thumbnail = request.getFile('thumbnailUpload')
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
        def videoUserObjectInstance
        if (params.id) {
            videoUserObjectInstance = VideoUserObject.get(params.id)
        } else {
            videoUserObjectInstance = new VideoUserObject()
        }
        videoUserObjectInstance.properties = params

        def owner = User.findByUsername(authenticateService.principal().username)
        videoUserObjectInstance.owner = owner
        videoUserObjectInstance.type = "video"
        
        // Handle uploaded file
        def uploadedFile = request.getFile('videoFile')
        if (!uploadedFile.empty) {
            if (uploadedFile.originalFilename.split("\\.")[-1].toLowerCase() in grailsApplication.config.openfurry.fileTypes.video) {
                def dest = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "video"), "${time}.${owner.username}_${uploadedFile.originalFilename}")
                uploadedFile.transferTo(dest)
                videoUserObjectInstance.file = dest.getCanonicalPath().replaceAll(servletContext.getRealPath("/"), '')
            } else {
                videoUserObjectInstance.errors.rejectValue("file", "openfurry.errors.fileTypeMismatch", "The uploaded file does not meet the approved file-type requirements")
            }
        }

        def thumbnail = request.getFile('thumbnailUpload')
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
        def flashUserObjectInstance
        if (params.id) {
            flashUserObjectInstance = FlashUserObject.get(params.id)
        } else {
            flashUserObjectInstance = new FlashUserObject()
        }
        flashUserObjectInstance.properties = params

        def owner = User.findByUsername(authenticateService.principal().username)
        flashUserObjectInstance.owner = owner
        flashUserObjectInstance.type = "flash"
        
        // Handle uploaded file
        def uploadedFile = request.getFile('flashFile')
        if (!uploadedFile.empty) {
            if (uploadedFile.originalFilename.split("\\.")[-1].toLowerCase() in grailsApplication.config.openfurry.fileTypes.flash) {
                def dest = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "flash"), "${time}.${owner.username}_${uploadedFile.originalFilename}")
                uploadedFile.transferTo(dest)
                flashUserObjectInstance.file = dest.getCanonicalPath().replaceAll(servletContext.getRealPath("/"), '')
            } else {
                flashUserObjectInstance.errors.rejectValue("file", "openfurry.errors.fileTypeMismatch", "The uploaded file does not meet the approved file-type requirements")
            }
        }

        def thumbnail = request.getFile('thumbnailUpload')
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
        def imageUserObjectInstance
        if (params.id) {
            imageUserObjectInstance = ImageUserObject.get(params.id)
        } else {
            imageUserObjectInstance = new ImageUserObject()
        }
        imageUserObjectInstance.properties = params

        def owner = User.findByUsername(authenticateService.principal().username)
        imageUserObjectInstance.owner = owner
        imageUserObjectInstance.type = "image"

        def time = new Date().getTime()
        def files

        // Handle uploaded file
        def uploadedFile = request.getFile('imageFile')
        if (!uploadedFile.empty) {
            if (uploadedFile.originalFilename.split("\\.")[-1].toLowerCase() in grailsApplication.config.openfurry.fileTypes.image) {
                def dest = fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "image")
                files = imagingService.createImageUserObjectFiles(owner, uploadedFile, dest, time)
                imageUserObjectInstance.thumbnail = files[2].getCanonicalPath().replaceAll(servletContext.getRealPath("/"), '')
                imageUserObjectInstance.sizedFile = files[1].getCanonicalPath().replaceAll(servletContext.getRealPath("/"), '')
                imageUserObjectInstance.fullFile = files[0].getCanonicalPath().replaceAll(servletContext.getRealPath("/"), '')
            } else {
                imageUserObjectInstance.errors.rejectValue("file", "openfurry.errors.fileTypeMismatch", "The uploaded file does not meet the approved file-type requirements")
            }
        }

        def thumbnail = request.getFile('thumbnailUpload')
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
        def textUserObjectInstance
        if (params.id) {
            textUserObjectInstance = TextUserObject.get(params.id)
        } else {
            textUserObjectInstance = new TextUserObject()
        }
        textUserObjectInstance.properties = params

        def owner = User.findByUsername(authenticateService.principal().username)
        textUserObjectInstance.owner = owner
        textUserObjectInstance.type = "text"
        textUserObjectInstance.journal = false
        def time = new Date().getTime()

        def uploadedFile = request.getFile('attachment')
        if (!uploadedFile.empty) {
            if (uploadedFile.originalFilename.split("\\.")[-1].toLowerCase() in grailsApplication.config.openfurry.fileTypes.text) {
                def dest = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "text"), "${time}.${owner.username}_${uploadedFile.originalFilename}")
                uploadedFile.transferTo(dest)
                textUserObjectInstance.attachmentFile = dest.getCanonicalPath().replaceAll(servletContext.getRealPath("/"), '')
            } else {
                textUserObjectInstance.errors.rejectValue("attachmentFile", "openfurry.errors.fileTypeMismatch", "The uploaded file does not meet the approved file-type requirements")
            }
        }

        def thumbnail = request.getFile('thumbnailUpload')
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
            if (textUserObjectInstance.attachmentFile != null) {
                def f = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "text"), uploadedFile.originalFilename)
                f.delete()
            }
            render(view: "text", model: [instance: textUserObjectInstance])
        }
    }
    def saveJournal = {
        def textUserObjectInstance
        if (params.id) {
            textUserObjectInstance = TextUserObject.get(params.id)
        } else {
            textUserObjectInstance = new TextUserObject()
        }
        textUserObjectInstance.properties = params

        def owner = User.findByUsername(authenticateService.principal().username)
        textUserObjectInstance.owner = owner
        textUserObjectInstance.type = "text"
        textUserObjectInstance.journal = true
        textUserObjectInstance.attachmentFile = null
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
        def applicationUserObjectInstance
        if (params.id) {
            applicationUserObjectInstance = ApplicationUserObject.get(params.id)
        } else {
            applicationUserObjectInstance = new ApplicationUserObject()
        }
        applicationUserObjectInstance.properties = params

        def owner = User.findByUsername(authenticateService.principal().username)
        applicationUserObjectInstance.type = "application"
        applicationUserObjectInstance.owner = owner
        def time = new Date().getTime()

        def uploadedFile = request.getFile("fileUpload")
        if (!uploadedFile.empty) {
                log.warn "Saving file..3"
            if (uploadedFile.originalFilename.split("\\.")[-1].toLowerCase() in grailsApplication.config.openfurry.fileTypes.application) {
                def dest = new File(fileUploadService.getSubmissionDirectory(servletContext.getRealPath("/"), owner, "application"), "${time}.${owner.username}_${uploadedFile.originalFilename}")
                uploadedFile.transferTo(dest)
                applicationUserObjectInstance.screenShot = dest.getCanonicalPath().replaceAll(servletContext.getRealPath("/"), '')
            } else {
                applicationUserObjectInstance.errors.rejectValue("screenShot", "openfurry.errors.fileTypeMismatch", "The uploaded file does not meet the approved file-type requirements")
            }
        }

        def thumbnail = request.getFile('thumbnailUpload')
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
        def orderedCollectionInstance
        if (params.id) {
            orderedCollectionInstance = OrderedCollection.get(params.id)
        } else {
            orderedCollectionInstance = new OrderedCollection()
        }
        orderedCollectionInstance.properties = params

        def owner = User.findByUsername(authenticateService.principal().username)
        orderedCollectionInstance.owner = owner
        orderedCollectionInstance.type = "orderedCollection"

        def time = new Date().getTime()
        def thumb = thumbnailService.saveThumbnail(request.getFile('thumbnailUpload'), owner, time, "oderedCollection", servletContext.getRealPath("/"))
        if (thumb) {
            orderedCollectionInstance.thumbnail = thumb
        } else {
            orderedCollectionInstance.errors.rejectValue("thumbnail", "openfurry.errors.fileTypeMismatch", "The uploaded file does not meet the approved file-type requirements")
        }

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
        def unorderedCollectionInstance
        if (params.id) {
            unorderedCollectionInstance = UnorderedCollection.get(params.id)
        } else {
            unorderedCollectionInstance = new UnorderedCollection()
        }
        unorderedCollectionInstance.properties = params

        def owner = User.findByUsername(authenticateService.principal().username)
        unorderedCollectionInstance.owner = owner
        unorderedCollectioninstance.type = "unorderedCollection"

        def time = new Date().getTime()
        def thumb = thumbnailService.saveThumbnail(request.getFile('thumbnailUpload'), owner, time, "unoderedCollection", servletContext.getRealPath("/"))
        if (thumb) {
            unorderedCollectionInstance.thumbnail = thumb
        } else {
            unorderedCollectionInstance.errors.rejectValue("thumbnail", "openfurry.errors.fileTypeMismatch", "The uploaded file does not meet the approved file-type requirements")
        }
            

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
