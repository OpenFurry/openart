package com.mjs_svc.openfurry

class ApplicationUserObject extends UserObject {
    String screenShot

    static constraints = {
        screenShot(blank: false)
    }
}
