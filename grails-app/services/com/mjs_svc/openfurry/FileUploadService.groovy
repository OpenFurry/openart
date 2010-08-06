package com.mjs_svc.openfurry

class FileUploadService {

    static transactional = false

    def getSubmissionDirectory(String realPath, User user, String submissionType) {
        def s = File.separatorChar
        def directory = new File(realPath, "${s}submissions${s}${submissionType}${s}${user.username}")
        directory.mkdirs()
        directory
    }

    def getThemeDirectory(String realPath, Long id) {
    }

    def getAvatarDirectory(String realPath, User user) {
        def s = File.separatorChar
        def directory = new File(realPath, "${s}avatars${s}${user.username}")
        directory.mkdirs()
        directory
    }
}
