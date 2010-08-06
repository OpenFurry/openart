package com.mjs_svc.openfurry

class UserStatus {
    String status
    Person owner

    static constraints = {
        status(maxSize: 140)
    }
}
