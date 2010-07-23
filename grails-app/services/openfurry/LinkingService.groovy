package openfurry

class LinkingService {

    static transactional = true

    def linkify(Boolean noImages, String text) {
        def g = new org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib()
        /*
         * Replace the following:
         * ~user -> a link to the user's page: '[user avatar]~user'
         * ~!user -> a link to the user's page: '[user avatar]'
         * #id -> a link to the submission with that id: '#id'
         * #!id -> a link to the submission with that id: '[submission thumbnail]'
         * 
         * Don't replace if it's escaped (i.e.: "We're \#1!" ":O\~\~")
         */
        // ~user
        text = text.replaceAll(/(?!(<=\\))~([a-zA-Z0-9_.-]+)/, {full, lookAhead, username -> 
            def p = Person.findByUsername(username)
            if (p) {
                if (noImages) {
                    "<a href=\"${g.createLink(controller: 'person', action: 'show', params: [username: username])}\">${full}</a>"
                } else {
                    """
                    <a href=\"${g.createLink(controller: 'person', action: 'show', params: [username: username])}\">
                        <img src=\"${g.createLinkTo(dir: 'avatars', file: p.avatar)}\" class=\"avatar\" align=\"middle\"/> ${full}
                    </a>
                    """
                }
            }
        })

        // ~!user
        text = text.replaceAll(/(?!(<=\\))~!([a-zA-Z0-9_.-]+)/, {full, lookAhead, username -> 
            def p = Person.findByUsername(username)
            if (p) {
                """
                <a href=\"${g.createLink(controller: 'person', action: 'show', params: [username: username])}\">
                    <img src=\"${g.createLinkTo(dir: 'avatars', file: p.avatar)}\" class=\"avatar\" align=\"middle\"/>
                </a>
                """
            } else {
                full
            }
        })

        // #id
        text = text.replaceAll(/(?!(<=\\))#(\d+)/, {full, lookAhead, submissionId ->
            "<a href=\"${g.createLink(controller: 'view', action: 'show', id: submissionId)}\">${full}</a>"
        })


        // #!id
        text = text.replaceAll(/(?!(<=\\))#!(\d+)/, {full, lookAhead, submissionId ->
            def s = UserObject.get(submissionId)

            if (s) {
                return """
                <a href=\"${g.createLink(controller: 'view', action: 'show', id: submissionId)}\">
                    <img src="${g.createLinkTo(file: s.thumbnail)}" class="uoLink" />
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
                    <a href=\"${g.createLink(controller: 'issue', action: 'show', id: issueId)}\">
                        ${(s.status == 3 || s.status == 4) ? "<s>issue:" + issueId + "</s>" : "issue:" + issueId}
                    </a>
                    """
            } else {
                return full
            }
        })

        // http://*
        text = text.replaceAll(/&lt;((?i)https?:\/\/[\p{Alnum}_=.:?&;%-]+)&gt;/, {full, url ->
            "<a target=\"_blank\" href=\"${url.replaceAll(/&amp;/, '&')}\">${url}</a>"
        })

        text
    }
}
