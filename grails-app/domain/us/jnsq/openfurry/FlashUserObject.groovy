package us.jnsq.openfurry

class FlashUserObject extends UserObject {
    String file

    static constraints = {
        file(blank: false)
    }
}
