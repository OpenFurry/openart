package us.jnsq.openart

class VideoUserObject extends UserObject {
    String file

    static constraints = {
        file(blank: false)
    }
}
