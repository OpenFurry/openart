package us.jnsq.openart

import org.springframework.web.context.request.RequestContextHolder as RCH

class MarketService {

    static transactional = true

    // Required to warn of attempted stealing
    def warningService

    // Required for checking if user can afford
    def permissionsService

    // Required for reason for warning
    def messageSource

    def makePayment(OAUser userFrom, OAUser userTo, Integer amount) {
        if (amount < 0) {
            if (!permissionsService.market.userCanAfford(unitPrice.price)) {
                // TODO message user
                return
            }
            userFrom.pennies -= amount
            userTo.pennies += ammount
            userFrom.save(flush: true)
            userTo.save(flush: true)
        } else {
            // Warn the user if they try to steal pennies
            def locale = RCH.currentRequestAttributes().getSession().locale
            warningService.warn(userFrom, grailsApplication.config.openart.user.warning.small, messageSource.getMessage("openart.warning.stealing", null, "A gift of negative pennies would be stealing, you have been warned.", locale))
        }
    }

    def transact(OAUser user, String signal) {
        // Try to grab the unit price
        def unitPrice = UnitPrice.findBySignal(signal)
        if (unitPrice) {
            if (!permissionsService.market.userCanAfford(unitPrice.price)) {
                // TODO message user
                return
            }
            // Modify the user's balance
            user.pennies -= unitPrice.price
            user.interactionCount++
            if (user.interactionCount > grailsApplication.config.openart.classes.threshold[user.memberClass] && user.memberClass < 4) {
                user.memberClass++
            }
            user.save(flush: true)

            // notify the user
            flash.transact = "openart.market.transact.${(unitPrice > 0) ? 'positive' : 'negative'}"
            flash.transactArgs = [ unitPrice.price.abs() ]
            flash.transactDefault = "You ${(unitPrice > 0) ? 'gained' : 'spent'} ${unitPrice.price} pennies"
        } // fail silently
    }
}
