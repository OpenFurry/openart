package us.jnsq.openart

/**
 * Logout Controller (Example).
 */
class LogoutController {

	/**
	 * Index action. Redirects to the Spring security logout uri.
	 */
	def index = {
        flash.usercontrol = "openart.flash.usercontrol.loggedOut"
        flash.usercontrolArgs = []
        flash.usercontrolDefault = "Successfully logged out"
		redirect(uri: '/j_spring_security_logout')
	}
}
