package openfurry

class MarketService {

    static transactional = true

    def makePayment(Person userFrom, Person userTo, amount) {
        if (ammount < 0) {
            userFrom.pennies -= amount
            userTo.pennies += ammount
            userFrom.save(flush: true)
            userTo.save(flush: true)
        } else {
            //TODO: warn of error
            userFrom.warningLevel += grailsApplication.config.openfurry.user.warning.small
            userFrom.save(flush: true)
        }
    }

    def transact(Person user, String component, String params, String action) {
        // Try to grab the unit price
        def unitPrice = UnitPrice.findByComponentAndParamsAndAction(component, params, action)
        if (unitPrice) {
            // Modify the user's balance
            user.pennies -= unitPrice.price
            user.save(flush: true)

            flash.transact = "openfurry.market.transact.${(unitPrice > 0) ? 'positive' : 'negative'}"
            flash.transactArgs = [ unitPrice.price.abs() ]
            flash.transactDefault = "You ${(unitPrice > 0) ? 'gained' : 'spent'} ${unitPrice.price} pennies"
        } else {
            //TODO: let the user know
        }
    }
}
