package openfurry

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

class Commission {
    Person artist
    Person commissioner
    CommissionInfo commissionInfo
    String details
    Integer status = CH.config.openfurry.commission.status.commissioned

    static constraints = {
        status(range:0..5)
    }
}
