package openfurry

class CommentService {

    static transactional = true

    def getCommentsForObject(obj) {
        def crit = Comment.createCriteria()
        def comments = c {
            and {
                eq("parentType", obj.class.toString)
                eq("parentId", obj.id)
            }
            order("id", "asc")
        }
    }
}
