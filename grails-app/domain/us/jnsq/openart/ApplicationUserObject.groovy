package us.jnsq.openart

class ApplicationUserObject extends UserObject {
    String screenShot

    static constraints = {
        screenShot(blank: false)
    }
}
