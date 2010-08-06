package com.mjs_svc.openfurry

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

class Commission {
    User artist
    User commissioner
    CommissionInfo commissionInfo
    String details
    Integer status = CH.config.openfurry.commissions.status.commissioned

    static constraints = {
        status(range:0..5)
    }
}
