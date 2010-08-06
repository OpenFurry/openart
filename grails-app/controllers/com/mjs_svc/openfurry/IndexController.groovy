package com.mjs_svc.openfurry

class IndexController {

    def listService

    def index = {
        def recents = listService.listUOsForRating({}, params)
        params.totalSubmissions = 16
        [uoList: recents]
    }
}
