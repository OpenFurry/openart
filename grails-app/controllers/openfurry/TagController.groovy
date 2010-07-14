package openfurry

class TagController {
    def listService
    def authenticateService

    def index = { }

    def list = { }

    def show = {
        def tag = Tag.findByTag(params.id)

        if (!tag) {
            response.sendError(404) // TODO i18n
            return
        }

        def criteria

        if (params.type) {
            criteria = {
                and {
                    eq('type', params.type)
                    tags {
                        eq('tag', tag)
                    }
                }
            }
        } else {
            criteria = {
                tags {
                    eq('tag', tag)
                }
            }
        }
        def list = listService.listForRating(criteria)

        [uoList: list]
    }
}
