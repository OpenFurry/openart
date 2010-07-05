package openfurry

import openfurry.Role
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

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

    /** User's prefered license */
    License preferedLicense

    /** Type of user */
    String memberType = "Lurker"

    /** Maximum rating that will appear in lists */
    String maxViewableRating = CH.config.openfurry.ratings.low

    /** Pennies used for transactions on the site */
    Long pennies = 0

    /** User's warning level */
    Integer warningLevel = 0

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
        warningLevel(min: 0, max: 100)
    }

    static mapping = {
        profile type: "text"
	}

	static transients = ['pass', 'constantsService']
	static hasMany = [authorities: Role, votes: IssueVote, userObjects: UserObject, comments: Comment]
	static belongsTo = Role
}
