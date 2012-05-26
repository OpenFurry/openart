package us.jnsq.openart

class LinkingTagLib {
    def linkingService

    static namespace = "of"

    def linking = { attrs, body ->
        /* Basically just call the linking service on the text */
        def text = attrs.text ?: body()
        out << linkingService.linkify(attrs['noImages'] ? true : false, text.toString())
    }
    
}
