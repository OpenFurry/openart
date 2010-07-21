package openfurry

import openfurry.Role
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

/**
 * User domain class.
 */
class Person {
	String username
	String userRealName
    String avatar = ",default.png"
	String passwd
	String pass = '[secret]'
	boolean enabled
	String email
    String profile
    Map properties
    Species species
    License preferedLicense
    Integer memberType = CH.config.openfurry.user.types.lurker
    Integer memberClass = CH.config.openfurry.user.classes.bronze
    Integer maxViewableRating = CH.config.openfurry.ratings.low
    Integer preferedTheme
    Long interactionCount = 1 // for joining
    Long pennies = 10
    Integer warningLevel = 0
    Date watchlistCursorUltimate
    Date watchlistCursorPenultimate
    Date watchlistCursorBookmark
    Date watchlistCursorBookmarkDate
    Boolean commissionStatus = false
    Boolean tradeStatus = false
    Boolean giftStatus = false

	static constraints = {
		username(blank: false, unique: true)
		userRealName(blank: true)
		passwd(blank: false)
		enabled()
        profile(blank: true, nullable: true)
        properties(blank: true, nullable: true)
        memberType(range: 0..9)
        memberClass(range: 0..4)
        maxViewableRating(range: 0..2)
        preferedTheme(blank: true, nullable: true)
        pennies()
        warningLevel(min: 0, max: 100)
        watchlistCursorUltimate(blank: true, nullable: true)
        watchlistCursorPenultimate(blank: true, nullable: true)
        watchlistCursorBookmark(blank: true, nullable: true)
        watchlistCursorBookmarkDate(blank: true, nullable: true)
    }

    static mapping = {
        profile type: "text"
	}

	static transients = ['pass']
	static hasMany = [
        authorities: Role, 
        votes: IssueVote, 
        userObjects: UserObject, 
        likes: UserObject,
        favorites: UserObject,
        friends: Person,
        watches: Person,
        watchedTags: Tag,
        comments: Comment, 
        statuses: UserStatus,
        groups: UserGroup,
        messages: UserMessage
        ]
	static belongsTo = Role
}
