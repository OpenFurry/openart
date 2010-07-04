package openfurry

import openfurry.Person

/**
 * Authority domain class.
 */
class Role {

	static hasMany = [people: Person]

	/** description */
	String description
	/** ROLE String */
	String authority

	static constraints = {
		authority(blank: false, unique: true)
		description()
	}
}
