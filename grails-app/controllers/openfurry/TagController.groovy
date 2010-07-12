package openfurry

class TagController {

    def index = { }

    def list = { }

    def show = {
        def tag = Tag.findByTag(params.id)

        if (!tag) {
            response.sendError(404) // TODO i18n
            return
        }

        def crit = TaggedItem.createCriteria()
        def taggedItems

        if (params.type) {
            taggedItems = crit.list {
                and {
                    userObject {
                        eq('type', params.type)
                    }
                    eq('tag', tag)
                }
            }
        } else {
            taggedItems = crit.list {
                eq('tag', tag)
            }
        }

        def userObjects = taggedItems.collect { it.userObject }
        
        [uoList: userObjects]
    }
}
