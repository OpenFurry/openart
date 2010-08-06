package com.mjs_svc.openfurry

class AudioUserObject extends UserObject {
    String file

    static constraints = {
        file(blank: false)
    }
}
