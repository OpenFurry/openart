package openfurry

class LinkingTagLib {

    static namespace = "of"

    def linking = { attrs, body ->
        /*
         * Replace the following:
         * ~user -> a link to the user's page: '[user avatar]~user'
         * ~!user -> a link to the user's page: '[user avatar]'
         * #id -> a link to the submission with that id: '#id'
         * #!id -> a link to the submission with that id: '[submission thumbnail]'
         */
    }

}
