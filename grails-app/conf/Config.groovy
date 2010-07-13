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
        grails.serverURL = "http://localhost:8082/"
    }
    test {
        grails.serverURL = "http://localhost:8082/"
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
 * Acegi constants
 */
security.defaultRole='ROLE_USER'

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
// TODO move to i18n
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
// TODO move repr to i18n, store codes in repr
openfurry.user.classes.repr = ["Bronze", "Silver", "Gold", "Platinum", "Black"]
openfurry.user.classes.bronze = 0
openfurry.user.classes.silver = 1
openfurry.user.classes.gold = 2
openfurry.user.classes.platinum = 3
openfurry.user.classes.black = 4
openfurry.user.classes.thresholds = [10, 100, 1000, 10000, 100000]

// User message types
// Range used in: (due to static typing)
// - grails-app/domain/UserMessage
openfurry.user.messageTypes.repr = ["success", "warning", "failure" ]
openfurry.user.messageTypes.success = 0
openfurry.user.messageTypes.warning = 1
openfurry.user.messageTypes.failure = 2

// User properties
openfurry.user.properties = [
    [key: 'openfurry.user.properties.website', default: 'Website', url: '{value}'],
    [key: 'openfurry.user.properties.furaffinity', default: 'FurAffinity user', url: 'http://furaffinity.net/user/{value}'],
    [key: 'openfurry.user.properties.badger', default: 'Badger! user', url: 'http://badgerific.com/badger/{value}', display: '<link rel="stylesheet" type="text/css" href="http://media.mjs-svc.com/badger/badge.css" /><span class="badgerjax-{value}"></span><script type="text/javascript" src="http://mjs-svc.com/js/jquery-1.4.2.min.js"></script><script type="text/javascript" src="http://badgerific.com/b/{value}/"></script>'],
    [key: 'openfurry.user.properties.aim', default: 'AOL Instant Messenger', display: '<a href="aim:addbuddy?screenname={value}">{value}</a>'],
    [key: 'openfurry.user.properties.yim', default: 'Yahoo! Messenger', display: '<a href="ymsgr:addfriend?{value}">{value}</a>'],
    [key: 'openfurry.user.properties.msnim', default: 'Windows Live Messenger', display: '<a href="msnim:add?contact={value}">{value}</a>'],
    [key: 'openfurry.user.properties.blog', default: 'Blog website', url: '{value}'],
    [key: 'openfurry.user.properties.os', default: 'Operating system of choice'],
    [key: 'openfurry.user.properties.shell', default: 'Shell of choice'],
]

// Rating levels
// Range used in: (due to static typing)
// - grails-app/domain/Person.groovy
// - grails-app/domain/UserObject.groovy
// TODO move to i18n
openfurry.ratings.repr = ["General", "Mature", "Adult"]
openfurry.ratings.low = 0
openfurry.ratings.medium = 1
openfurry.ratings.high = 2

// Maximum upload sizes in MBytes
openfurry.maxUploadSize = [
    audio: 10,
    video: 50,
    flash: 50,
    image: 5,
    text: 2,
    application: 1
]

// Acceptable filetypes
openfurry.fileTypes = [
    audio: ["wav", "mp3", "mid", "ogg"],
    video: ["mpg", "mpeg", "mp4", "m4v", "avi", "ogm", "flv"],
    flash: ["swf"],
    image: ["jpg", "jpeg", "gif", "png"],
    text: ["pdf", "doc", "docx", "odt", "rtf", "txt"],
    application: ["jpg", "jpeg", "gif", "png"]
]

// Default thumbnail icons
// Path from web-apps
openfurry.defaultIcons = [
    audio: "/submissions/thumbs/,default-audio.png",
    video: "/submissions/thumbs/,default-video.png",
    flash: "/submissions/thumbs/,default-flash.png",
    image: "/submissions/thumbs/,default-image.png",
    text: "/submissions/thumbs/,default-text.png",
    audio: "/submissions/thumbs/,default-audio.png",
]

// OpenFurry commissions
openfurry.commissions.status.repr = ["Commissioned", "Accepted", "Rejected", "Paid", "In-progress", "Completed"]
openfurry.commissions.status.commissioned = 0
openfurry.commissions.status.accepted = 1
openfurry.commissions.status.rejected = 2
openfurry.commissions.status.paid = 3
openfurry.commissions.status.inprogress = 4
openfurry.commissions.status.completed = 5
