package openfurry

class CommentService {

    static transactional = true

    def getAllCommentsForObject(obj) {
        Comment.withCriteria {
            and {
                eq("parentType", obj.class.toString().split('\\.')[-1])
                eq("parentId", obj.id.toString())
            }
            order("id", "asc")
        }
    }

    def getCommentCountForObject(obj) {
        Comment.withCriteria {
            and {
                eq("parentType", obj.class.toString().split('\\.')[-1])
                eq("parentId", obj.id.toString())
            }
        }.size()
    }

    def getCommentsForObjectAndParent(obj, parent) {
        Comment.withCriteria {
            and {
                eq("parentType", obj.class.toString().split('\\.')[-1])
                eq("parentId", obj.id.toString())
                eq("parentComment", parent)
            }
            order("id", "asc")
        }
    }

    def getCommentsForObjectWithNoParent(obj) {
        Comment.withCriteria {
            and {
                eq("parentType", obj.class.toString().split('\\.')[-1])
                eq("parentId", obj.id.toString())
                isNull("parentComment")
            }
            order("id", "asc")
        }
    }
}
