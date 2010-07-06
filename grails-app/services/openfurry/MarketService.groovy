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

    def purchaseItem(Person user, String signal, String params, String action) {
        // Try to grab the unit price
        def unitPrice = UnitPrice.findBySignalAndParamsAndAction(signal, params, action)
        if (unitPrice) {
            // Modify the user's balance
            user.pennies -= unitPrice.price
            user.save(flush: true)

            //TODO: let the user know with a flash
        } else {
            //TODO: let the user know
        }
    }
}
