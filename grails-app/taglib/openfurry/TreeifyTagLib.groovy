package openfurry

class TreeifyTagLib {

    static namespace = "of"

    def speciesOptions = { attrs ->
        out << _speciesOptions(openfurry.Species.createCriteria().list() { isNull("parent") }, 0)
    }

    private String _speciesOptions(species, depth) {
        StringBuffer toReturn = new StringBuffer()
        species.each { it ->
            toReturn.append("<option value=\"${it.id}\">")
            (0..depth).each { i -> toReturn.append('-') }
            toReturn.append("${it.speciesName}</option>")
            toReturn.append(_speciesOptions(openfurry.Species.findAllWhere(parent: it), depth + 1) + '\n')
        }
        toReturn.toString()
    }

    def categoryOptions = { attrs ->
        out << _categoryOptions(openfurry.Category.createCriteria().list() { isNull("parent") }, 0)
    }

    private String _categoryOptions(category, depth) {
        StringBuffer toReturn = new StringBuffer()
        category.each { it ->
            toReturn.append("<option value=\"${it.id}\">")
            (0..depth).each { i -> toReturn.append('-') }
            toReturn.append("${it.categoryName}</option>")
            toReturn.append(_categoryOptions(openfurry.Category.findAllWhere(parent: it), depth + 1))
        }
        toReturn.toString()
    }

}
