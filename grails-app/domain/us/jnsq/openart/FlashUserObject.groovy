package us.jnsq.openart

class FlashUserObject extends UserObject {
    String file

    static constraints = {
        file(blank: false)
    }
}
