package openfurry

class MOTD {
    Date dateCreated
    Date lastUpdated
    String content
    Boolean active = true
    String status

    static constraints = {
        content(maxSize: 500)
        status(inList: ['notice', 'warning', 'error'])
    }
}
