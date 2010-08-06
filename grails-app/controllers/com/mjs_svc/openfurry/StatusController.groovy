package com.mjs_svc.openfurry

class StatusController {

    static defaultAction = 'recent'

    def recent = {
    }

    def list = {
        def person = Person.findByUsername(params.username)
        if (!person) {
            response.sendError(404) // TODO i18n
            return
        }
    }

    def mentions = {}

    def save = {}

    def delete = {}
}
