package openfurry

class CommentTagLib {

    static namespace = "of"

    def commentService

    def linkingService

    def commentForm = { attrs ->
        out << """
            <form method="post" action="${createLink(controller: 'comment', action: 'post')}">
                <table style="width: auto">
                    <thead>
                        <tr>
                            <th colspan="2">${message(code: 'openfurry.comment.views.post', default: 'Post comment')}</th>
                        </tr
                    </thead>
                    <tbody>
                        <tr class="prop">
                            <th class="name">${message(code: 'openfurry.comment.title', default: 'Title')}</th>
                            <td class="value"><input type="text" name="title"  value="${attrs['defaultTitle'] ?: ''}" /></td>
                        </tr>
                        <tr class="prop">
                            <th class="name">${message(code: 'openfurry.comment.body', default: 'Comment')}</th>
                            <td class="value"><textarea name="comment" rows="10" cols="50"></textarea></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="submit" /></td>
                        </tr>
                    </tbody>
                </table>
                <input type="hidden" name="objectType" value="${attrs['object'].class.toString().split('\\.')[-1]}" />
                <input type="hidden" name="objectId" value="${attrs['object'].id}" />
                <input type="hidden" name="parentId" value="${attrs['parentId'] ?: ''}" />
                <input type="hidden" name="targetURI" value="${(request.forwardURI - request.contextPath)}" />
            </form>
        """
    }

    def commentsForObject = { attrs ->
        out << _treeify(attrs['object'], commentService.getCommentsForObjectWithNoParent(attrs['object']))
    }

    private String _treeify(obj, comments) {
        def toReturn = new StringBuffer()
        comments.each {
            toReturn.append("""
                <div class="comment">
                    <div class="commentAuthor">
                        <a name="c${it.id}"></a>${linkingService.linkify(false, '~' + it.owner.username)}
                    </div>
                    <div class="commentTitle">
                        <strong>${message(code: 'openfurry.comment.title', default: 'Title')}:</strong> ${it.title}
                    </div>
                    <div class="commentBody">
                        ${it.comment}
                    </div>
                    <div class="replyForm hide">
                        ${commentForm(object: obj, parentId: it.id, defaultTitle: 'RE: ' + it.title)}
                    </div>
                    <div class="subComments">
                        ${_treeify(obj, commentService.getCommentsForObjectAndParent(obj, it))}
                    </div>
                </div>
            """)
        }
        toReturn.toString()
    }
        
}
