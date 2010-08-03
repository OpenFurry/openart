package openfurry

class MarketController {
    static defaultAction = "index"

    def marketService

    def index = {}

    def prices = {
        [prices: UnitPrice.list()]
    }

    def store = {}

    def purchase = {}
}
