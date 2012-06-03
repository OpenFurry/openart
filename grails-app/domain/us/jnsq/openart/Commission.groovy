package us.jnsq.openart

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

class Commission {
    OAUser artist
    OAUser commissioner
    CommissionInfo commissionInfo
    String details
    Integer status = CH.config.openart.commissions.status.commissioned

    static constraints = {
        status(range:0..5)
    }
}
