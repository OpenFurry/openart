
package us.jnsq.openart

class OrderedCollection extends UserObject {
    SortedSet userObjects
    static hasMany = [userObjects: UserObject]
}
