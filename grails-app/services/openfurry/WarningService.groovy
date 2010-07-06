package openfurry

class WarningService {

    static transactional = true

    def warn(Person user, Integer level, String reason) {
        if (user.warningLevel + level > 100) {
            ban(user)
        } else {
            user.warningLevel += level
            user.save(flush: true)
        }
    }

    def praise(Person user, Integer level, String reason) {
        if (user.warningLevel - level < 0) {
            user.warningLevel = 0
        } else {
            user.warningLevel -= level
        }
        user.save(flush: true)
    }

    def ban(Person user) {
        user.warningLevel = 100
        user.enabled = false
        user.save(flush: true)
    }

    def unban(Person user) {
    }
}
