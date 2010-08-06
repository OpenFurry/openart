
package com.mjs_svc.openfurry

class OrderedCollection extends UserObject {
    SortedSet userObjects
    static hasMany = [userObjects: UserObject]
}
