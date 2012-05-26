package us.jnsq.openart

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

class ThumbnailService {
    def imagingService
    def fileUploadService

    static transactional = false

    def saveThumbnail(multiPartFile, owner, time, type, path) {
        if (!multiPartFile.empty) {
            if (multiPartFile.originalFilename.split("\\.")[-1].toLowerCase() in CH.config.openart.fileTypes.image) {
                def dest = new File(fileUploadService.getSubmissionDirectory(path, owner, "thumbs"), "${time}.${owner.username}_${multiPartFile.originalFilename}")
                imagingService.createThumbnailFile(multiPartFile, dest)
                return dest.getCanonicalPath().replaceAll(path, '')
            } else {
                return null
            }
        } else {
            return CH.config.openart.defaultIcons[type]
        }
    }
}
