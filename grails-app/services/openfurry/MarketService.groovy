package openfurry

import org.springframework.web.context.request.RequestContextHolder as RCH

class MarketService {

    static transactional = true

    // Required to warn of attempted stealing
    def warningService

    // Required for reason for warning
    def messageSource

    def makePayment(Person userFrom, Person userTo, Integer amount) {
        if (amount < 0) {
            userFrom.pennies -= amount
            userTo.pennies += ammount
            userFrom.save(flush: true)
            userTo.save(flush: true)
        } else {
            // Warn the user if they try to steal pennies
            def locale = RCH.currentRequestAttributes().getSession().locale
            warningService.warn(userFrom, grailsApplication.config.openfurry.user.warning.small, messageSource.getMessage("openfurry.warning.stealing", null, "A gift of negative pennies would be stealing, you have been warned.", locale))
        }
    }

    def transact(Person user, component, params, action) {
        // Try to grab the unit price
        def unitPrice = UnitPrice.withCriteria(uniqueResult: true) {
            and {
                eq("component", component)
                eq("params", params)
                eq("action", action)
            }
        }
        if (unitPrice) {
            // Modify the user's balance
            user.pennies -= unitPrice.price
            user.save(flush: true)

            // notify the user
            flash.transact = "openfurry.market.transact.${(unitPrice > 0) ? 'positive' : 'negative'}"
            flash.transactArgs = [ unitPrice.price.abs() ]
            flash.transactDefault = "You ${(unitPrice > 0) ? 'gained' : 'spent'} ${unitPrice.price} pennies"
        } // fail silently
    }
}
