package com.mjs_svc.openfurry

class ListController {
    def listService

    def user = { 
        def user = Person.findByUsername(params.id)
        if (!user) {
            response.sendError(404)
            return
        }

        def criteria = {
            eq("owner", user)
        }
        def submissions = listService.listUOsForRating(criteria, params)
        params.totalSubmissions = submissions.totalCount

        [user: user, uoList: submissions, params: params]
    }

    def friends = {
        def user = Person.findByUsername(params.id)
        def criteria = {
            "in"("owner", user.friends)
        }
        def submissions = listService.listUOsForRating(criteria, params)
        params.totalSubmissions = submissions.totalCount

        [user: user, uoList: submissions, params: params]
    }
}
