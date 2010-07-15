package openfurry

import grails.orm.HibernateCriteriaBuilder as HCB
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

class ListService {
    def authenticateService

    static transactional = true

    def listUOsForRating(Closure c) {
        def maxRating = CH.config.openfurry.ratings.low
        if (authenticateService.isLoggedIn()) {
            def p = Person.findByUsername(authenticateService.principal().username)
            maxRating = p.maxViewableRating
        }
        
        UserObject.withCriteria {
            and {
                c.delegate = delegate
                c() 
                le('rating', maxRating)
            }
        }

    }
}
