package us.jnsq.openart

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

class Commission {
    User artist
    User commissioner
    CommissionInfo commissionInfo
    String details
    Integer status = CH.config.openart.commissions.status.commissioned

    static constraints = {
        status(range:0..5)
    }
}
