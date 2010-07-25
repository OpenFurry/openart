package openfurry

class LinkingService {

    static transactional = true

    def linkify(Boolean noImages, String text) {
        def g = new org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib()
        /*
         * Replace the following:
         * <img*> -> cleaned html (disallow markdown images)
         * ~user -> a link to the user's page: '[user avatar]~user'
         * ~!user -> a link to the user's page: '[user avatar]'
         * #id -> a link to the submission with that id: '#id'
         * #!id -> a link to the submission with that id: '[submission thumbnail]'
         * issue:id -> a link to the issue with that id: 'issue:id'
         * comment:id -> a link to the comment with that id: 'comment:id'
         * group:slug -> a link to the group with that slug: 'group:slug'
         * thread:id -> a link to the thread with that id: 'thread:id'
         * &lt;http://example.com&gt; -> a link to example.com (since markdown is called after encodeAsHTML
         * 
         * Don't replace if it's escaped (i.e.: "We're \#1!" ":O\~\~")
         */
        text = text.replaceAll(/(<)(img[^>]+)(>)/, { full, lbracket, innards, rbracket ->
            return "&lt;${innards}&gt;"
        })

        // ~user
        text = text.replaceAll(/(?!(<=\\))~([a-zA-Z0-9_.-]+)/, {full, lookAhead, username -> 
            def p = Person.findByUsername(username)
            if (p) {
                if (noImages) {
                    "<a style=\"display: inline\" href=\"${g.createLink(controller: 'person', action: 'show', params: [username: username])}\">${full}</a>"
                } else {
                    """
                    <a href=\"${g.createLink(controller: 'person', action: 'show', params: [username: username])}\">
                        <img src=\"${g.resource(dir: 'avatars', file: p.avatar)}\" class=\"avatar\" align=\"middle\"/> ${full}
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
                    <img src=\"${g.resource(dir: 'avatars', file: p.avatar)}\" class=\"avatar\" align=\"middle\"/>
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
                    <img src="${g.resource(file: s.thumbnail)}" class="uoLink" />
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

        text = text.replaceAll(/group:(\S+)/, {full, groupSlug ->
            "<a href=\"${g.createLink(controller: 'group', action: 'show', id: groupSlug)}\">${full}</a>"
        })

        // thread:id
        text = text.replaceAll(/thread:(\d+)/, { full, threadId ->
            "<a href=\"${g.createLink(controller: 'group', action: 'thread', id: threadId)}\">${full}</a>"
        })

        // comment:id
        text = text.replaceAll(/comment:(\d+)/, { full, commentId ->
            def c = Comment.get(commentId)

            if (c) {
                def controller
                def action
                switch (c.parentType) {
                    case "AudioUserObject":
                    case "VideoUserObject":
                    case "FlashUserObject":
                    case "ImageUserObject":
                    case "TextUserObject":
                    case "ApplicationUserObject":
                    case "OrderedCollection":
                    case "UnorderedCollection":
                        controller = "view"
                        action = "show"
                        break
                    case "Issue":
                        controller = "issue"
                        action = "show"
                        break
                    case "GroupPost":
                        controller = "group"
                        action = "thread"
                        break
                    default:
                        controller = "view"
                        action = "show"
                }
                return "<a href=\"${g.createLink(controller: controller, action: action, id: c.parentId)}#c${commentId}\">${full}</a>"
            } else {
                return full
            }
        })

        // &lt;http://*%gt;
        text = text.replaceAll(/&lt;((?i)https?:\/\/[\p{Alnum}_=.:?&;%-]+)&gt;/, {full, url ->
            "<a target=\"_blank\" href=\"${url.replaceAll(/&amp;/, '&')}\">${url}</a>"
        })

        text
    }
}
