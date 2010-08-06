package com.mjs_svc.openfurry

class CommentService {

    static transactional = true

    def deleteCommentsForObject(obj) {
        getAllCommentsForObject(obj).each {
            it.delete()
        }
    }

    def getAllCommentsForObject(obj) {
        Comment.withCriteria {
            and {
                eq("parentType", obj.class.toString().split("\\.")[-1])
                eq("parentId", obj.id.toString())
            }
            order("id", "asc")
        }
    }

    def getCommentCountForObject(obj) {
        Comment.withCriteria {
            and {
                eq("parentType", obj.class.toString().split("\\.")[-1])
                eq("parentId", obj.id.toString())
            }
        }.size()
    }

    def getCommentsForObjectAndParent(obj, parent) {
        Comment.withCriteria {
            and {
                eq("parentType", obj.class.toString().split("\\.")[-1])
                eq("parentId", obj.id.toString())
                eq("parentComment", parent)
            }
            order("id", "asc")
        }
    }

    def getCommentsForObjectWithNoParent(obj) {
        Comment.withCriteria {
            and {
                eq("parentType", obj.class.toString().split("\\.")[-1])
                eq("parentId", obj.id.toString())
                isNull("parentComment")
            }
            order("id", "asc")
        }
    }

    def getCommentTree(obj) {
        def list = Comment.withCriteria {
            and {
                eq("parentType", obj.class.toString().split("\\.")[-1])
                eq("parentId", obj.id.toString())
            }
            order("id", "asc")
        }

        def tree = []

        list.each {
            _treeify(tree, it)
        }

        tree
    }

    private _treeify(tree, comment) {
        if (comment.parentComment) {
            tree.each {
                if (comment.parentComment == it['comment']) {
                    it['subComments'].add([comment: comment, subComments: []])
                } else {
                    if (it['subComments'].size() > 0) {
                        _treeify(it['subComments'], comment)
                    }
                }
            }
        } else {
            tree.add([comment: comment, subComments: []])
        }
    }
}
