package openfurry

import grails.orm.HibernateCriteriaBuilder as HCB
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

class ListService {
    def authenticateService

    static transactional = true

    def listUOsForRating(Closure c, String type) {
        def maxRating = CH.config.openfurry.ratings.low
        def p = null
        if (authenticateService.isLoggedIn()) {
            p = authenticateService.principal().domainClass
            maxRating = p.maxViewableRating
        }
        
        UserObject.withCriteria {
            and {
                c.delegate = delegate
                c() 
                le('rating', maxRating)
                if (type) {
                    eq('type', type)
                }
                if (p) {
                    or {
                        eq('published', true)
                        and {
                            eq('published', false)
                            eq('owner', p)
                        }
                    }
                } else {
                    eq('published', true)
                }
            }
            order('lastUpdated', 'desc')
        }

    }
}
