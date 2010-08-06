package com.mjs_svc.openfurry

class StatusController {

    static defaultAction = 'recent'

    def recent = {
    }

    def list = {
        def user = User.findByUsername(params.username)
        if (!user) {
            response.sendError(404) // TODO i18n
            return
        }
    }

    def mentions = {}

    def save = {}

    def delete = {}
}
