package openfurry

import openfurry.Role

/**
 * User domain class.
 */
class Person {
	/** Username */
	String username

	/** User Display Name*/
	String userRealName

	/** MD5 Password */
	String passwd
	/** plain password to create a MD5 password */
	String pass = '[secret]'

	/** enabled */
	boolean enabled
    /** user email */
	String email

    /** user profile */
    String profile

    /** User's species */
    Species species

    /** Type of user */
    String memberType = "Lurker"

    /** Maximum rating that will appear in lists */
    String maxViewableRating = "General"

    /** Pennies used for transactions on the site */
    Long pennies = 0

	static constraints = {
		username(blank: false, unique: true)
		userRealName(blank: true)
		passwd(blank: false)
		enabled()
        profile(blank: true, nullable: true)
        memberType(inList: [
            "Lurker",
            "Visual artist",
            "Sculptor",
            "Textile artist",
            "Composer",
            "Videographer",
            "Flash artist",
            "Programmer"
            ])
        maxViewableRating(inList: ["General", "Mature", "Adult"])
        pennies()
    }

    static mapping = {
        profile type: "text"
	}

	static transients = ['pass']
	static hasMany = [authorities: Role]
	static belongsTo = Role
}
