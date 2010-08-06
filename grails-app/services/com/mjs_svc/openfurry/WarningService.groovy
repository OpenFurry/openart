package com.mjs_svc.openfurry

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

class WarningService {

    static transactional = true

    def warn(Person user, Integer level, String reason) {
        if (user.warningLevel + level > CH.config.openfurry.user.warning.max) {
            ban(user)
        } else {
            user.warningLevel += level
            user.save(flush: true)
        }
    }

    def praise(Person user, Integer level, String reason) {
        if (user.warningLevel - level < CH.config.openfurry.user.warning.min) {
            user.warningLevel = CH.config.openfurry.user.warning.min
        } else {
            user.warningLevel -= level
        }
        user.save(flush: true)
    }

    def ban(Person user) {
        user.warningLevel = CH.config.openfurry.user.warning.max
        user.enabled = false
        user.save(flush: true)
    }

    def unban(Person user) {
        user.warningLevel = CH.config.openfurry.user.warning.max - CH.config.openfurry.user.warning.large
        user.enabled = true
        user.save(flush: true)
    }
}
