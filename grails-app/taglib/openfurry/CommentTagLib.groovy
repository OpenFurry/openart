package openfurry

class CommentTagLib {

    static namespace = "of"

    def commentService

    def linkingService

    def markdownService

    def commentForm = { attrs ->
        out << """
            <form method="post" action="${createLink(controller: 'comment', action: 'post')}">
                <table>
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

    def commentCountForObject = { attrs ->
        out << commentService.getCommentCountForObject(attrs['object'])
    }

    def commentsForObject = { attrs ->
        out << _treeify(attrs['object'], commentService.getCommentTree(attrs['object']), 0)
    }

    private String _treeify(obj, comments, depth) {
        comments.collect {
            """
                <div class="comment block${depth > 10 ? ' depthExceeded' : ''}">
                    <div class="commentTitle">
                        ${it.comment.title ? it.comment.title.encodeAsHTML() : '<em>' + message(code: 'openfurry.comment.notitle', default: 'No title') + '</em>'}
                    </div>
                    <div class="commentAuthor">
                        <a name="c${it.comment.id}"></a>${linkingService.linkify(false, '~' + it.comment.owner.username)}
                    </div>
                    <div class="commentBody">
                        ${linkingService.linkify(false, markdownService.markdown(it.comment.comment.encodeAsHTML()))}
                    </div>
                    <div class="commentLinks">
                        <a href="javascript:\$('#creply${it.comment.id}').toggle()">${message(code: 'openfurry.comment.reply.comment', default: 'Reply to comment')}</a>
                    </div>
                    <div class="replyForm hide" id="creply${it.comment.id}">
                        ${commentForm(object: obj, parentId: it.comment.id, defaultTitle: 'RE: ' + it.comment.title)}
                    </div>
                    <div class="subComments">
                        ${_treeify(obj, it.subComments, depth + 1)}
                    </div>
                </div>
            """
        }.join("")
    }
        
}
