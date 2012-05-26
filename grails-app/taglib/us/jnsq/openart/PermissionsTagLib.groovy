package us.jnsq.openart

class PermissionsTagLib {
    static namespace = "of"

    def permissionsService

    def withPermission = { attrs, body ->
        if (permissionsService."${attrs['class']}"."${attrs['permission']}"(attrs['arg'])) {
            out << body()
        } else {
            out << ''
        }
    }

    def hasPermission = { attrs ->
        out << permissionsService."${attrs['class']}"."${attrs['permission']}"(attrs['arg'])
    }
}
