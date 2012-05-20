package us.jnsq.openfurry

class CategoryController {
    def authenticateService
    def listService
    
    static defaultAction = 'list'

    def list = {}

    def show = {
        def c = Category.get(params.id)
        if (!c) {
            response.sendError(404) // TODO i18n
            return
        }

        def criteria = {
            categories {
                eq('id', c.id)
            }
        }

        def list = listService.listUOsForRating(criteria, params)

        [uoList: list, category: c]
    }
}
