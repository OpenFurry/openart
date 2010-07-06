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
    Integer memberType = CH.config.openfurry.user.types.lurker

    /** User class */
    Integer memberClass = CH.config.openfurry.user.classes.bronze

    /** Maximum rating that will appear in lists */
    Integer maxViewableRating = CH.config.openfurry.ratings.low

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
        memberType(range: 0..9)
        memberClass(range: 0..4)
        maxViewableRating(range: 0..2)
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
