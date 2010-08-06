package com.mjs_svc.openfurry

class VideoUserObject extends UserObject {
    String file

    static constraints = {
        file(blank: false)
    }
}
