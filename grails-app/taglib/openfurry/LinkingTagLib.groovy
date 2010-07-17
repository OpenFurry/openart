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
         * 
         * Don't replace if it's escaped (i.e.: "We're \#1!" ":O\~\~")
         */
        def text = attrs.text ?: body()

        // ~user
        text = text.replaceAll(/(?!(<=\\))~([a-zA-Z0-9_-]+)/, {full, lookAhead, username -> 
            def p = Person.findByUsername(username)
            if (p) {
                """
                <a href=\"${createLink(controller: 'person', action: 'show', params: [username: username])}\">
                    <img src=\"${createLinkTo(dir: 'avatars', file: p.avatar)}\" class=\"avatar\" align=\"middle\"/> ${full}
                </a>
                """
            }
        })

        // ~!user
        text = text.replaceAll(/(?!(<=\\))~!([a-zA-Z0-9_-]+)/, {full, lookAhead, username -> 
            def p = Person.findByUsername(username)
            if (p) {
                """
                <a href=\"${createLink(controller: 'person', action: 'show', params: [username: username])}\">
                    <img src=\"${createLinkTo(dir: 'avatars', file: p.avatar)}\" class=\"avatar\" align=\"middle\"/>
                </a>
                """
            } else {
                full
            }
        })

        // #id
        text = text.replaceAll(/(?!(<=\\))#(\d+)/, {full, lookAhead, submissionId ->
            "<a href=\"${createLink(controller: 'view', action: 'show', id: submissionId)}\">${full}</a>"
        })


        // #!id
        text = text.replaceAll(/(?!(<=\\))#!(\d+)/, {full, lookAhead, submissionId ->
            def s = UserObject.get(submissionId)

            if (s) {
                return """
                <a href=\"${createLink(controller: 'view', action: 'show', id: submissionId)}\">
                    <img src="${createLinkTo(file: s.thumbnail)}" class="uoLink" />
                </a>
                """
            } else {
                return full
            }
        })

        // issue:id
        text = text.replaceAll(/issue:(\d+)/, { full, issueId ->
            def s = Issue.get(issueId)

            if (s) {
                return """
                    <a href=\"${createLink(controller: 'issues', action: 'show', id: issueId)}\">
                        ${(s.status == 3 || s.status == 4) ? "<s>issue:" + issueId + "</s>" : "issue:" + issueId}
                    </a>
                    """
            } else {
                return full
            }
        })

        out << text
    }

}
