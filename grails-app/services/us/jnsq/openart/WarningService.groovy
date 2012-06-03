package us.jnsq.openart

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

class WarningService {

    static transactional = true

    def warn(OAUser user, Integer level, String reason) {
        if (user.warningLevel + level > CH.config.openart.user.warning.max) {
            ban(user)
        } else {
            user.warningLevel += level
            user.save(flush: true)
        }
    }

    def praise(OAUser user, Integer level, String reason) {
        if (user.warningLevel - level < CH.config.openart.user.warning.min) {
            user.warningLevel = CH.config.openart.user.warning.min
        } else {
            user.warningLevel -= level
        }
        user.save(flush: true)
    }

    def ban(OAUser user) {
        user.warningLevel = CH.config.openart.user.warning.max
        user.user.enabled = false
        user.save(flush: true)
    }

    def unban(OAUser user) {
        user.warningLevel = CH.config.openart.user.warning.max - CH.config.openart.user.warning.large
        user.user.enabled = true
        user.save(flush: true)
    }
}
