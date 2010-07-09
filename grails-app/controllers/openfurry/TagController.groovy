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

        def userObjects = []
        def mappings = [
            audio: AudioUserObject.class,
            video: VideoUserObject.class,
            flash: FlashUserObject.class,
            image: ImageUserObject.class,
            text: TextUserObject.class,
            application: ApplicationUserObject.class
            ]
        for (taggedItem in tag.taggedItems) {
            if (params.type) {
                if (taggedItem.userObject.class == (mappings[params.type])) {
                    userObjects.add(taggedItem.userObject)
                }
            } else {
                userObjects.add(taggedItem.userObject)
            }
        }
        [uoList: userObjects]
    }
}
