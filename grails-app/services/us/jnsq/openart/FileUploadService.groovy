package us.jnsq.openart

class FileUploadService {

    static transactional = false

    def getSubmissionDirectory(String realPath, OAUser user, String submissionType) {
        def s = File.separatorChar
        def directory = new File(realPath, "${s}submissions${s}${submissionType}${s}${user.user.username}")
        directory.mkdirs()
        directory
    }

    def getThemeDirectory(String realPath, Long id) {
    }

    def getAvatarDirectory(String realPath, OAUser user) {
        def s = File.separatorChar
        def directory = new File(realPath, "${s}avatars${s}${user.user.username}")
        directory.mkdirs()
        directory
    }
}
