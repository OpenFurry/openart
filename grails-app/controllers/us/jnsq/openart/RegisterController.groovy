package us.jnsq.openart

import org.springframework.security.providers.UsernamePasswordAuthenticationToken as AuthToken
import org.springframework.security.context.SecurityContextHolder as SCH

/**
 * Registration controller.
 */
class RegisterController {
    static defaultAction = "index"

	def authenticateService
	def daoAuthenticationProvider
	def emailerService
    def messagingService

	static Map allowedMethods = [save: 'POST', update: 'POST']

	/**
	 * User Registration Top page.
	 */
	def index = {

		// skip if already logged in
		if (authenticateService.isLoggedIn()) {
			redirect action: show
			return
		}

		if (session.id) {
			def user = new User()
			user.properties = params
			return [user: user, tstart: new Date().getTime()]
		}

		redirect(uri: '/')
	}

	/**
	 * User Information page for current user.
	 */
	def show = {

		// get user id from session's domain class.
		def user = authenticateService.userDomain()
		if (user) {
			render(view: 'show', model: [user: User.get(user.id)])
		}
		else {
			redirect(action: index)
		}
	}

	/**
	 * Edit page for current user.
	 */
	def edit = {

		def user = authenticateService.userDomain()
		if (user) {
			user = User.get(user.id)
		}

		if (!user) {
            response.sendError(404) // TODO i18n
			return
		}

		[user: user]
	}

	/**
	 * update action for current user's edit page
	 */
	def update = {

		def user = authenticateService.userDomain()
		if (user) {
			user = User.get(user.id)
		}
		else {
			redirect(action: index)
			return
		}

		if (!user) {
            reponse.sendError(404) // TODO i18n
			return
		}

		// if user want to change password. leave passwd field blank, passwd will not change.
		if (params.passwd && params.passwd.length() > 0
				&& params.repasswd && params.repasswd.length() > 0) {
			if (params.passwd == params.repasswd) {
				user.passwd = authenticateService.encodePassword(params.passwd)
			}
			else {
				user.passwd = ''
                user.errors.rejectValue("passwd", "openart.errors.passwordMismatch", "The passwords you entered did not match")
				render view: 'edit', model: [user: user]
				return
			}
		}

		user.userRealName = params.userRealName
		user.email = params.email
		if (params.emailShow) {
			user.emailShow = true
		}
		else {
			user.emailShow = false
		}

		if (user.save()) {
			redirect action: show, id: user.id
		}
		else {
			render view: 'edit', model: [user: user]
		}
	 }

	/**
	 * User save action.
	 */
	def save = {

		// skip if already logged in
		if (authenticateService.isLoggedIn()) {
			redirect(action: show)
			return
		}

		def user = new User()
		user.properties = params

        // Redo if they triggered bot defenses (taking less than 5 seconds or filling in the honey pot
        if ((new Date().getTime() - Long.parseLong(params.tstart) < 5000l) || params.hp) {
            /*
            Do not change the flash message!

            This is a little bit of social engineering - if we tell them their passwords mismatched, they will hopefully
            take longer to fill out the form and not trigger the 5 second rule.

            ~MJS
            */
            user.errors.rejectValue("passwd", "openart.errors.passwordMismatch", "The passwords you entered did not match")
			render(view: 'index', model: [user: user, tstart: new Date().getTime()])
            return
        }

        if (params.username =~ /[^a-zA-Z_0-9-]/) {
            user.errors.rejectValue("username", "openart.user.username.allowedChars", "The allowed characters for usernames are letters, numbers, hyphens (-), and underscores (_)")
			render(view: 'index', model: [user: user, tstart: new Date().getTime()])
			return
        }

		def config = authenticateService.securityConfig
		def defaultRole = config.security.defaultRole

		def role = Role.findByAuthority(defaultRole)
		if (!role) {
			user.passwd = ''
			flash.message = 'Default Role not found.' // If this happens, the DB is down, and we have bigger things to worry about
			render(view: 'index', model: [user: user, tstart: new Date().getTime()])
			return
		}

        // Check for mismatched or blank passwords
		if (params.passwd != params.repasswd || params.passwd == '') {
			user.passwd = ''
            user.errors.rejectValue("passwd", "openart.errors.passwordMismatch", "The passwords you entered did not match")
			render(view: 'index', model: [user: user, tstart: new Date().getTime()])
			return
		}
        
        // Check for invitation code if we need it
        if (grailsApplication.config.openart.requireInvitation) {
            def invitation = UserInvitation.findByCode(params.invitationCode)
            if (!invitation) {
                user.errors.rejectValue("enabled", "openart.userInvitation.doesNotExist", "Could not find that invitation code, please check your typing - this is case sensitive!")
                render(view: "index", model: [user: user, tstart: new Date().getTime()])
                return
            } else {
                invitation.delete()
            }
        }


		def pass = authenticateService.encodePassword(params.passwd)
		user.passwd = pass
		user.enabled = true
		user.profile = ''
		if (user.save()) {
			role.addToPeople(user)
            /*
			if (config.security.useMail) {
				String emailContent = """You have signed up for an account at:

 ${request.scheme}://${request.serverName}:${request.serverPort}${request.contextPath}

 Here are the details of your account:
 -------------------------------------
 LoginName: ${user.username}
 Email: ${user.email}
 Full Name: ${user.userRealName}
 Password: ${params.passwd}
"""

				def email = [
					to: [user.email], // 'to' expects a List, NOT a single email address
					subject: "[${request.contextPath}] Account Signed Up",
					text: emailContent // 'text' is the email body
				]
				emailerService.sendEmails([email])
			}
            */

			user.save(flush: true)

			def auth = new AuthToken(user.username, params.passwd)
			def authtoken = daoAuthenticationProvider.authenticate(auth)
			SCH.context.authentication = authtoken
			redirect uri: '/'
		} else {
			user.passwd = ''
			render(view: 'index', model: [user: user, tstart: new Date().getTime()])
		}
	}
}
