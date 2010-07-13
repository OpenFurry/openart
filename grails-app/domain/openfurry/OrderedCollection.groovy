
package openfurry

class OrderedCollection extends UOCollection {
    SortedSet userObjects
    static hasMany = [userObjects: UserObject]
}
