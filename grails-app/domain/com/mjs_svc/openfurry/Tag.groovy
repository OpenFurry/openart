package com.mjs_svc.openfurry

class Tag {
    String tag

    static constraints = {
        tag(maxSize: 60)

    }

    static hasMany = [taggedItems: UserObject]

    static belongsTo = UserObject
}
