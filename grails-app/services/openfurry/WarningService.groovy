package openfurry

class WarningService {

    static transactional = true

    def warn(Person user, Integer level, String reason) {
        if (user.warningLevel + level > grailsApplication.config.openfurry.user.warning.max) {
            ban(user)
        } else {
            user.warningLevel += level
            user.save(flush: true)
        }
    }

    def praise(Person user, Integer level, String reason) {
        if (user.warningLevel - level < grailsApplication.config.openfurry.user.warning.min) {
            user.warningLevel = grailsApplication.config.openfurry.user.warning.min
        } else {
            user.warningLevel -= level
        }
        user.save(flush: true)
    }

    def ban(Person user) {
        user.warningLevel = grailsApplication.config.openfurry.user.warning.max
        user.enabled = false
        user.save(flush: true)
    }

    def unban(Person user) {
        user.warningLevel = grailsApplication.config.openfurry.user.warning.max - grailsApplication.config.openfurry.user.warning.large
        user.enabled = true
        user.save(flush: true)
    }
}
