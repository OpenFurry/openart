package us.jnsq.openfurry

import grails.orm.HibernateCriteriaBuilder as HCB
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

class ListService {
    def authenticateService

    static transactional = true

    def listUOsForRating(Closure c, params) {
        params.max = Math.min(params?.max?.toInteger() ?: 16, 100)
        params.offset = params?.offset?.toInteger() ?: 0

        def maxRating = CH.config.openfurry.ratings.low
        def p = null
        if (authenticateService.isLoggedIn()) {
            p = authenticateService.principal().domainClass
            maxRating = p.maxViewableRating
        }
        
        UserObject.createCriteria().list(
            max: params.max,
            offset: params.offset,
            sort: "lastUpdated",
            order: "desc"
        ) {
            and {
                c.delegate = delegate
                c() 
                le('rating', maxRating)
                if (params?.type) {
                    eq('type', params.type)
                }
                if (p && !authenticateService.ifAnyGranted("ROLE_STAFF,ROLE_ADMIN")) {
                    and {
                        or {
                            eq('published', true)
                            and {
                                eq('published', false)
                                eq('owner', p)
                            }
                        }
                        or {
                            eq('takenDown', false)
                            and {
                                eq('takenDown', true)
                                eq('owner', p)
                            }
                        }
                    }
                } else {
                    eq('published', true)
                }
            }
        }

    }

    def listGroupPosts(group, params) {
        params.max = Math.min(params?.max?.toInteger() ?: 10, 100)
        params.offset = params?.offset?.toInteger() ?: 0

        GroupPost.createCriteria().list(
            max: params.max, 
            offset: params.offset,
            sort: "lastUpdated",
            order: "desc"
        ) {
            eq("group", group)
        }
    }
}
