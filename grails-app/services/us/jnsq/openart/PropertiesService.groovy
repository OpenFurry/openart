package us.jnsq.openart

class PropertiesService {

    static transactional = false

    def toMap(String properties) {
        def toReturn = [:]
        properties.split("\n").each {
            toReturn.add([name: it.split("=::")[0], value: it.split("=::")[1]])
        }
        toReturn
    }

    def toProperties(map) {
        def properties = new StringBuffer()
        map.each {
            properties.append("${it.name}=::${it.value}\n")
        }
        properties.toString()
    }

}
