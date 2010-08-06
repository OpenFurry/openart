package com.mjs_svc.openfurry

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

    static mapping = {
        table 'auth_role'
    }
}
