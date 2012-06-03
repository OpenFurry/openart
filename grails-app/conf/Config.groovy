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
    root {
        warn 'stdout'
    }
    appenders {
        console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    }

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
 * OpenArt settings
 */
openart.version.number = "0.1"
openart.version.name.latin = "Acinonyx jubatus"
openart.version.name.english = "Cheetah"
//openart.assetURL = "http://assets.openart.com"
openart.readOnly = false
openart.requireInvitation = true
openart.enabledTypes.image = true
openart.enabledTypes.audio = true
openart.enabledTypes.video = true
openart.enabledTypes.flash = true
openart.enabledTypes.text = true
openart.enabledTypes.application = true
openart.permalinkBase = "http://o-f.co"
openart.mediaplayer.player = "http://media.mjs-svc.com/of/player.swf"
openart.mediaplayer.swfobject = "http://media.mjs-svc.com/of/swfobject.js"

/**
 * OpenArt Constants
 */

// Warning levels
openart.user.warning.small = 1
openart.user.warning.medium = 5
openart.user.warning.large = 10
openart.user.warning.min = 0
openart.user.warning.max = 100

// User types
// Range used in: (due to static typing)
// - grails-app/domain/Person.groovy
// - grails-app/domain/UserObject.groovy
// TODO move to i18n, put codes in repr
openart.user.types.repr = ["lurker", "visualArtist", "sculptor", "textileArtist", "composer", "videographer", "flashArtist", "programmer", "performer", "fursuiter"]
openart.user.types.lurker = 0
openart.user.types.visualArtist = 1
openart.user.types.sculptor = 2
openart.user.types.textileArtist = 3
openart.user.types.composer = 4
openart.user.types.videographer = 5
openart.user.types.flashArtist = 6
openart.user.types.programmer = 7
openart.user.types.performer = 8
openart.user.types.fursuiter = 9

// User classes
// Range used in: (due to static typing)
// - grails-app/domain/Person.groovy
// TODO move repr to i18n, store codes in repr
openart.user.classes.repr = ["Bronze", "Silver", "Gold", "Platinum", "Black"]
openart.user.classes.bronze = 0
openart.user.classes.silver = 1
openart.user.classes.gold = 2
openart.user.classes.platinum = 3
openart.user.classes.black = 4
openart.user.classes.thresholds = [10, 100, 1000, 10000, 100000]

// User message types
// Range used in: (due to static typing)
// - grails-app/domain/UserMessage
openart.user.messageTypes.repr = ["success", "warning", "failure", "transaction"]
openart.user.messageTypes.success = 0
openart.user.messageTypes.warning = 1
openart.user.messageTypes.failure = 2
openart.user.messageTypes.transaction = 3

// User properties
openart.user.properties = [
    [key: 'openart.user.properties.website', default: 'Website', display: '<a href="{value}" target="_blank">{value}</a>'],
    [key: 'openart.user.properties.furaffinity', default: 'FurAffinity user', display: '<a href="http://furaffinity.net/user/{value}" target="_blank">{value}</a>'],
    [key: 'openart.user.properties.badger', default: 'Badger! user', display: '<link rel="stylesheet" type="text/css" href="http://media.mjs-svc.com/badger/badge.css" /><span class="badgerjax-{value}"></span><script type="text/javascript" src="http://mjs-svc.com/js/jquery-1.4.2.min.js"></script><script type="text/javascript" src="http://badgerific.com/b/{value}/"></script>'],
    [key: 'openart.user.properties.aim', default: 'AOL Instant Messenger', display: '<a href="aim:addbuddy?screenname={value}">{value}</a>'],
    [key: 'openart.user.properties.yim', default: 'Yahoo! Messenger', display: '<a href="ymsgr:addfriend?{value}">{value}</a>'],
    [key: 'openart.user.properties.msnim', default: 'Windows Live Messenger', display: '<a href="msnim:add?contact={value}">{value}</a>'],
    [key: 'openart.user.properties.blog', default: 'Blog website', display: '<a href="{value}" target="_blank">{value}</a>'],
    [key: 'openart.user.properties.os', default: 'Operating system of choice', display: '{value}'],
    [key: 'openart.user.properties.shell', default: 'Shell of choice', display: '{value}'],
]

// Rating levels
// Range used in: (due to static typing)
// - grails-app/domain/Person.groovy
// - grails-app/domain/UserObject.groovy
// TODO move to i18n, put codes in repr
openart.ratings.repr = ["General", "Mature", "Adult"]
openart.ratings.low = 0
openart.ratings.medium = 1
openart.ratings.high = 2

// Maximum upload sizes in MBytes
openart.maxUploadSize = [
    audio: 10,
    video: 50,
    flash: 50,
    image: 5,
    text: 2,
    application: 1
]

// Acceptable filetypes
openart.fileTypes = [
    audio: ["wav", "mp3", "mid", "ogg"],
    video: ["mpg", "mpeg", "mp4", "m4v", "avi", "ogm", "flv"],
    flash: ["swf"],
    image: ["jpg", "jpeg", "gif", "png"],
    text: ["pdf", "doc", "docx", "odt", "rtf", "txt"],
    application: ["jpg", "jpeg", "gif", "png"]
]

// Default thumbnail icons
// Path from web-apps
openart.defaultIcons = [
    audio: "/submissions/thumbs/,default-audio.png",
    video: "/submissions/thumbs/,default-video.png",
    flash: "/submissions/thumbs/,default-flash.png",
    image: "/submissions/thumbs/,default-image.png",
    text: "/submissions/thumbs/,default-text.png",
    audio: "/submissions/thumbs/,default-audio.png",
]

// OpenArt commissions
// TODO move to i18n, put codes in repr
openart.commissions.status.repr = ["Commissioned", "Accepted", "Rejected", "Paid", "In-progress", "Completed"]
openart.commissions.status.commissioned = 0
openart.commissions.status.accepted = 1
openart.commissions.status.rejected = 2
openart.commissions.status.paid = 3
openart.commissions.status.inprogress = 4
openart.commissions.status.completed = 5

// OpenArt issue statuses
// TODO move to i18n, put codes in repr
openart.issue.status.repr = [
    "Suggestion", 
    "Seconded", 
    "Accepted", 
    "Completed", 
    "Rejected"
]
openart.issue.status.suggestion = 0
openart.issue.status.seconded = 1
openart.issue.status.accepted = 2
openart.issue.status.completed = 3
openart.issue.status.rejected = 4


// OpenArt issue types
openart.issue.type.repr = ["Bug", "Improvement", "NewFeature", "ToSViolation", "AUPViolation", "AccountIssue", "Harrassment"]
openart.issue.type.bug = 0
openart.issue.type.improvement = 1
openart.issue.type.newFeature = 2
openart.issue.type.violation = 3
openart.issue.type.AUPViolation = 4
openart.issue.type.accountIssue = 5
openart.issue.type.harrassment = 6

// Group categories
// Count used in UserGroup domain
openart.group.category.repr = ["Location", "GeneralInterest", "AdultInterest", "Species", "ArtMedium", "Other"]
openart.group.category.location = 0
openart.group.category.generalInterest = 1
openart.group.category.adultInterest = 2
openart.group.category.species = 3
openart.group.category.artMedium = 4
openart.group.category.other = 5

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'us.jnsq.openart.security.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'us.jnsq.openart.security.UserRole'
grails.plugins.springsecurity.authority.className = 'us.jnsq.openart.security.Role'
grails.plugins.springsecurity.requestMap.className = 'us.jnsq.openart.security.RequestMap'
grails.plugins.springsecurity.securityConfigType = 'Requestmap'
