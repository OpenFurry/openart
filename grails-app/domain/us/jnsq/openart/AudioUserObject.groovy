package us.jnsq.openart

class AudioUserObject extends UserObject {
    String file

    static constraints = {
        file(blank: false)
    }
}
