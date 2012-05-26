package us.jnsq.openart

class Species {
    String speciesName
    Species parent

    static constraints = {
        speciesName(maxSize: 60, blank: false)
        parent(nullable: true)
    }

    static hasMany = [subSpecies: Species, users: User]

    static belongsTo = Species
}
