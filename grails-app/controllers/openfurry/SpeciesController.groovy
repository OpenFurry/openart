package openfurry

class SpeciesController {
    def authenticateService
    def listService

    static defaultAction = 'list'

    def list = {
    }

    def show = {
        def s = Species.get(params.id)
        if (!s) {
            response.sendError(404) // TODO i18n
            return
        }

        def criteria = {
            species {
                eq('id', s.id)
            }
        }

        def list = listService.listUOsForRating(criteria, params.type)

        [uoList: list, species: s]
    }

    def create = {}

    def save = {}

    def edit = {}
}
