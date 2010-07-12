
package openfurry

class OrderedCollection extends UserObject {
    SortedSet userObjects
    static hasMany = [userObjects: UserObject]
}
