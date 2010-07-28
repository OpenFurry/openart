package openfurry

class TagController {
    def listService
    def authenticateService

    static defaultAction = 'list'

    def list = {
        /*
         * Generate a tag cloud - tags should be sized appropriately for how much they're used
         *
         * Much of this was inspired by django-tagging: http://django-tagging.googlecode.com
         */
        // Get all tags
        def tags = Tag.list()

        if (tags.size() == 0) {
            return
        }

        // Get the count for each tag
        def counts = tags.collect {
            it.taggedItems.size()
        }

        // Get the max and min
        def maxWeight = counts.max()
        def minWeight = counts.min()

        // Calculate thresholds for 6 steps
        def delta = (double)((maxWeight - minWeight) / 5)
        def thresholds = (1..6).collect {
            minWeight + it * delta
        }

        // Create our tag cloud
        def cloud = tags.collect {
            // If maxWeight is 1, then do a linear distribution; otherwise, do a log distribution
            def tagWeight = maxWeight == 1 ? it.taggedItems.size() : Math.log(it.taggedItems.size()) * maxWeight / Math.log(maxWeight)
            def fontSet = false
            def fontSize
            (0..5).each { st ->
                // If we're within the threshold, set the weight and get out of the closure
                if (!fontSet && tagWeight <= thresholds[st]) {
                    fontSize = st + 1
                    fontSet = true
                }
            }
            // Cloud should contain a list of maps of tags with their font-sizes
            [tag: it, fontSize: fontSize]
        }

        [tagCloud: cloud]
    }

    def show = {
        def tag = Tag.findByTag(params.id)

        if (!tag) {
            response.sendError(404) // TODO i18n
            return
        }

        def criteria = {
            tags {
                eq('tag', tag.tag)
            }
        }
        def list = listService.listUOsForRating(criteria, params)

        def watched = false
        if (authenticateService.isLoggedIn()) {
            def person = Person.get(authenticateService.principal().domainClass.id)
            if (tag in person.watchedTags) {
                watched = true
            }
        }

        [uoList: list, watched: watched]
    }
}
