package com.mjs_svc.openfurry

class FileUploadService {

    static transactional = false

    def getSubmissionDirectory(String realPath, Person user, String submissionType) {
        def s = File.separatorChar
        def directory = new File(realPath, "${s}submissions${s}${submissionType}${s}${user.username}")
        directory.mkdirs()
        directory
    }

    def getThemeDirectory(String realPath, Long id) {
    }

    def getAvatarDirectory(String realPath, Person user) {
        def s = File.separatorChar
        def directory = new File(realPath, "${s}avatars${s}${user.username}")
        directory.mkdirs()
        directory
    }
}
