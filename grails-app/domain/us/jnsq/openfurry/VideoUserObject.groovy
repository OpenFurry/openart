package us.jnsq.openfurry

class VideoUserObject extends UserObject {
    String file

    static constraints = {
        file(blank: false)
    }
}
