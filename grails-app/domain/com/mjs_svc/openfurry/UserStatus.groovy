package com.mjs_svc.openfurry

class UserStatus {
    String status
    User owner

    static constraints = {
        status(maxSize: 140)
    }
}
