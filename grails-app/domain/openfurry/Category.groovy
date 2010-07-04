package openfurry

class Category {
    String categoryName
    Category parent

    static constraints = {
        categoryName(maxSize: 60, blank: false)
        parent(nullable: true)
    }

    static hasMany = [subcategories: Category]

    static belongsTo = Category
}
