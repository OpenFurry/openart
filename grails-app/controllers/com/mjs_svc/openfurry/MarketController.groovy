package com.mjs_svc.openfurry

class MarketController {
    static defaultAction = "index"

    def marketService

    def index = {}

    def prices = {
        [prices: UnitPrice.list()] // TODO pagination
    }

    def store = {
        [trinkets: Trinket.list()] // TODO pagination
    }

    def purchase = {
        def trinket = Trinket.get(params.id)
        if (!trinket) {
            response.sendError(404) // TODO i18n
            return
        }

        // TODO check if user can afford trinket

        def newTrinket = new UserTrinket(trinket: trinket, owner: authenticateService.principal().domainClass)

        if (newTrinket.save(flush: true)) {
            // TODO message user, charge user
            redirect(action: "store")
        } else {
            // what?
            redirect(action: "store")
        }
    }

    def gift = {
        def trinket = Trinket.get(params.id)
        if (!trinket) {
            response.sendError(404) // TODO i18n
            return
        }

        def userTo = User.findByUsername(params.to)
        if (!userTo) {
            response.sendError(404)
            return
        }

        // TODO check if user can afford trinket

        def newTrinket = new UserTrinket(trinket: trinket, owner: userTo, userFrom: authenticateService.principal().domainClass)
        
        if (newTrinket.save(flush: true)) {
            // TODO charge user, message user
            redirect(action: "store")
        } else {
            // TODO Message user
            redirect(action: "store")
        }
    }

    def give = {
        def userTo = User.findByUsername(params.id)
        if (!userTo) {
            response.sendError(404)
            return
        }

        marketService.makePayment(User.get(authenticateService.principal().domainClass.id), userTo, params.amount)

        redirect(controller: "user", action: "show", params: [username: userTo.username])
    }
}
