package openfurry

class OrderedCollection extends Collection {
    SortedSet userObjects
    static hasMany = [userObjects: UserObject]
}
