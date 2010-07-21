package openfurry

class TreeifyTagLib {

    static namespace = "of"

    def speciesOptions = { attrs ->
        out << openfurry.Species.createCriteria().list() { isNull("parent") }.collect {
            "<optgroup label=\"${it.speciesName}\">\n${_speciesOptions(it.subSpecies, 1)}\n</optgroup>\n"
        }
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

    def speciesString = { attrs ->
        out << "<a href=\"${createLink(controller: 'species', action: 'show', id: attrs['species'].id)}\">${_speciesString(attrs['species'])}</a>"
    }

    private String _speciesString(s) {
        StringBuffer str = new StringBuffer()
        if (s?.parent) {
            str.append(_speciesString(s?.parent))
            str.append(" -&gt; ")
        }
        str.append(s?.speciesName)
        str.toString()
    }

    def speciesList = { attrs ->
        def list = Species.withCriteria {
            if (attrs['parent']) {
                eq('parent', attrs['parent'])
            } else {
                isNull('parent')
            }
        }
        out << """
        <ul>
            ${_speciesList(list)}
        </ul>"""
    }

    private String _speciesList(s) {
        StringBuffer str = new StringBuffer()
        s.each {
            str.append("<li><a href=\"${createLink(controller: 'species', action: 'show', id: it.id)}\">${it.speciesName}</a>")
            def children = it.subSpecies
            if (children.size() > 0) {
                str.append("\n<ul>\n")
                children.each { ch ->
                    str.append(_speciesList(ch))
                }
                str.append("\n</ul>\n")
            }
            str.append("</li>\n")
        }
        str.toString()
    }

    def categoryOptions = { attrs ->
        out << openfurry.Category.createCriteria().list() { isNull("parent") }.collect {
            "<optgroup label=\"${it.categoryName}\">\n${_categoryOptions(it.subcategories, 1)}\n</optgroup>\n"
        }
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

    def categoryString = { attrs ->
        out << "<a href=\"${createLink(controller: 'category', action: 'show', id: attrs['category'].id)}\">${_categoryString(attrs['category'])}</a>"
    }

    private String _categoryString(s) {
        StringBuffer str = new StringBuffer()
        if (s.parent) {
            str.append(_categoryString(s.parent))
            str.append(" -&gt; ")
        }
        str.append(s.categoryName)
        str.toString()
    }

    def categoryList = { attrs ->
        def list = Category.withCriteria {
            if (attrs['parent']) {
                eq('parent', attrs['parent'])
            } else {
                isNull('parent')
            }
        }
        out << """
        <ul>
            ${_categoryList(list)}
        </ul>"""
    }

    private String _categoryList(s) {
        StringBuffer str = new StringBuffer()
        s.each {
            str.append("<li><a href=\"${createLink(controller: 'category', action: 'show', id: it.id)}\">${it.categoryName}</a>")
            def children = it.subcategories
            if (children.size() > 0) {
                str.append("\n<ul>\n")
                children.each { ch ->
                    str.append(_categoryList(ch))
                }
                str.append("\n</ul>\n")
            }
            str.append("</li>\n")
        }
        str.toString()
    }
}
