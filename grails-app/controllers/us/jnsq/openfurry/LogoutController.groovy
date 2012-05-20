package us.jnsq.openfurry

/**
 * Logout Controller (Example).
 */
class LogoutController {

	/**
	 * Index action. Redirects to the Spring security logout uri.
	 */
	def index = {
        flash.usercontrol = "openfurry.flash.usercontrol.loggedOut"
        flash.usercontrolArgs = []
        flash.usercontrolDefault = "Successfully logged out"
		redirect(uri: '/j_spring_security_logout')
	}
}
