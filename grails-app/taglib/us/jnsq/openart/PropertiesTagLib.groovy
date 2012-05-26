package us.jnsq.openart

class PropertiesTagLib {
    static namespace = "of"

    def userProperty = { attrs ->
        def propMap
        grailsApplication.config.openart.user.properties.each {
            if (it.key == attrs.key) {
                propMap = it
                return
            }
        }
        out << """
        <tr class="prop">
            <th class="name">${message(code: propMap.key, default: propMap.default)}</th>
            <td class="value">${propMap.display.replaceAll(/\{value\}/, attrs.value)}</td>
        </tr>
        """
    }

    def propertyForm = { attrs ->
        out << """
        <tr>
            <td>
                <select name="key">
        """
        grailsApplication.config.openart.user.properties.each {
            out << """<option value="${it.key}">${message(code: it.key, default: it.default)}</option>"""
        }
        out << """
                </select>
            </td>
            <td>
                <input type="text" name="value" />
            </td>
        </tr>
        """
    }
}
