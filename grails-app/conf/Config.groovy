// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]
// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable for AppEngine!
grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []

// set per-environment serverURL stem for creating absolute links
environments {
    production {
        grails.serverURL = "http://www.changeme.com"
    }
    development {
        grails.serverURL = "http://localhost:8080/${appName}"
    }
    test {
        grails.serverURL = "http://localhost:8080/${appName}"
    }

}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'
}


//log4j.logger.org.springframework.security='off,stdout'

/**
 * OpenFurry Constants
 */

// Warning levels
openfurry.user.warning.small = 1
openfurry.user.warning.medium = 5
openfurry.user.warning.large = 10
openfurry.user.warning.min = 0
openfurry.user.warning.max = 100

// User types
// Range used in: (due to static typing)
// - grails-app/domain/Person.groovy
// - grails-app/domain/UserObject.groovy
openfurry.user.types.repr = ["Lurker", "Visual artist", "Sculptor", "Textile artist", "Composer", "Videographer", "Flash artist", "Programmer", "Performer", "Fursuiter"]
openfurry.user.types.lurker = 0
openfurry.user.types.visualArtist = 1
openfurry.user.types.sculptor = 2
openfurry.user.types.textileArtist = 3
openfurry.user.types.composer = 4
openfurry.user.types.videographer = 5
openfurry.user.types.flashArtist = 6
openfurry.user.types.programmer = 7
openfurry.user.types.performer = 8
openfurry.user.types.fursuiter = 9

// User classes
// Range used in: (due to static typing)
// - grails-app/domain/Person.groovy
openfurry.user.classes.repr = ["Bronze", "Silver", "Gold", "Platinum", "Black"]
openfurry.user.classes.bronze = 0
openfurry.user.classes.silver = 1
openfurry.user.classes.gold = 2
openfurry.user.classes.platinum = 3
openfurry.user.classes.black = 4

// Rating levels
// Range used in: (due to static typing)
// - grails-app/domain/Person.groovy
// - grails-app/domain/UserObject.groovy
openfurry.ratings.repr = ["General", "Mature", "Adult"]
openfurry.ratings.low = 0
openfurry.ratings.medium = 1
openfurry.ratings.high = 2
