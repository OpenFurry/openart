package us.jnsq.openfurry

class License {
    String title
    String description
    String display
    String url

    static constraints = {
        title(maxSize: 120)
        description(maxSize: 500)
        display(maxSize: 120)
        url(url: true)
    }

    static hasMany = [userObjects: UserObject, preferedLicenseForUsers: User, userObjects: UserObject]

    String toString() {
        this.title
    }
}
