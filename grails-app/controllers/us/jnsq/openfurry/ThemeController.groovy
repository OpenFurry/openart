package us.jnsq.openfurry

class ThemeController {
    static defaultAction = "list"

    def authenticateService

    def marketService

    def messagingService

    def show = {
        def theme = Theme.get(params.id)
        if (!theme) {
            // should fail gracefully instead
            flash.message = "Broken theme!" // TODO i18n
            redirect action: list
            return
        }
        def themeStyle = new ConfigSlurper().parse(theme.style)
        response.setHeader("Content-type", "text/css")
        [theme: themeStyle]
    }


    def save = {
        def styleText = params.theme.findAll { w ->
            w.getKey().contains('.') && w.getValue() != ""
        }.collect {
            "${it.getKey()}=\"${it.getValue()}\""
        }.join("\n")
            
        def themeInstance = new Theme()

        themeInstance.style = styleText

        def owner=User.findByUsername(authenticateService.principal().username)
        themeInstance.creator = owner

        themeInstance.name = params.name ? params.name : "${owner.username}'s theme"
        themeInstance.description = params.description

        if(themeInstance.save(flush: true)) {
            owner.preferedTheme = themeInstance.id
            owner.save(flush: true)

            // Save theme to a file for static hosting
            def themeStyle = new ConfigSlurper().parse(themeInstance.style)
            String css = g.render(template: "/theme/show", model: [theme: themeStyle]).toString()
            def themeDir = new File("${servletContext.getRealPath("/")}${File.separatorChar}themes${File.separatorChar}${themeInstance.id}")
            themeDir.mkdirs()
            def themeFile = new File(themeDir, "main.css").asWritable()
            themeFile.setText(css)

            // TODO transaction, messaging service
            marketService.transact(owner, "Theme.create(memberClass:${owner.memberClass})")

            flash.message = 
                "${message(code: 'default.created.message', args: [message(code: 'theme.label', default: 'Theme'), params.id])}"
            redirect(uri: "/")
        } else {
            render(view: "create", model: [instance: themeInstance])
        }
    }

    def scaffold = Theme
}
