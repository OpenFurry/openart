package com.mjs_svc.openfurry

import org.springframework.security.context.SecurityContextHolder
import org.springframework.security.providers.UsernamePasswordAuthenticationToken
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUserImpl
import org.springframework.security.GrantedAuthority
import org.springframework.security.GrantedAuthorityImpl

/**
 * Refresh the authentication token in the security context holder
 * cribbed from <http://blog.lourish.com/2010/03/10/updating-the-logged-in-user-with-acegispring-security-in-grails/>
 */
class RefreshUserService {

    static transactional = true

    def authenticateService

    def refresh() {
        def user = User.get(authenticateService.principal().domainClass.id)
        GrantedAuthority[] auths = user.authorities.collect {
            new GrantedAuthorityImpl(it.authority)
        }
        def updatedUser = new GrailsUserImpl(
            user.username,
            "",
            true,
            true,
            true,
            true,
            auths,
            user
        )

        def authToken = new UsernamePasswordAuthenticationToken(
            updatedUser, '', auths
        )

        SecurityContextHolder.context.authentication = authToken
    }
}
