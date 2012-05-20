package us.jnsq.openfurry

class ApplicationUserObject extends UserObject {
    String screenShot

    static constraints = {
        screenShot(blank: false)
    }
}
