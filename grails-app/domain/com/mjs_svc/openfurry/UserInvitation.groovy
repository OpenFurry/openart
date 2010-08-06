package com.mjs_svc.openfurry

class UserInvitation {
    String code

    static constraints = {
        code(maxSize: 10)
    }
}
