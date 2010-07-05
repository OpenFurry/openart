package openfurry

class FlatpageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [flatpageInstanceList: Flatpage.list(params), flatpageInstanceTotal: Flatpage.count()]
    }

    def create = {
        def flatpageInstance = new Flatpage()
        flatpageInstance.properties = params
        return [flatpageInstance: flatpageInstance]
    }

    def save = {
        def flatpageInstance = new Flatpage(params)
        if (flatpageInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'flatpage.label', default: 'Flatpage'), flatpageInstance.id])}"
            redirect(action: "show", id: flatpageInstance.id)
        }
        else {
            render(view: "create", model: [flatpageInstance: flatpageInstance])
        }
    }

    def show = {
        def flatpageInstance = Flatpage.get(params.id)
        if (!flatpageInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'flatpage.label', default: 'Flatpage'), params.id])}"
            redirect(action: "list")
        }
        else {
            [flatpageInstance: flatpageInstance]
        }
    }

    def edit = {
        def flatpageInstance = Flatpage.get(params.id)
        if (!flatpageInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'flatpage.label', default: 'Flatpage'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [flatpageInstance: flatpageInstance]
        }
    }

    def update = {
        def flatpageInstance = Flatpage.get(params.id)
        if (flatpageInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (flatpageInstance.version > version) {
                    
                    flatpageInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'flatpage.label', default: 'Flatpage')] as Object[], "Another user has updated this Flatpage while you were editing")
                    render(view: "edit", model: [flatpageInstance: flatpageInstance])
                    return
                }
            }
            flatpageInstance.properties = params
            if (!flatpageInstance.hasErrors() && flatpageInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'flatpage.label', default: 'Flatpage'), flatpageInstance.id])}"
                redirect(action: "show", id: flatpageInstance.id)
            }
            else {
                render(view: "edit", model: [flatpageInstance: flatpageInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'flatpage.label', default: 'Flatpage'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def flatpageInstance = Flatpage.get(params.id)
        if (flatpageInstance) {
            try {
                flatpageInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'flatpage.label', default: 'Flatpage'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'flatpage.label', default: 'Flatpage'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'flatpage.label', default: 'Flatpage'), params.id])}"
            redirect(action: "list")
        }
    }
}
