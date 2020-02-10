package soporte

import grails.gorm.transactions.Transactional

import soporte.MiembroEquipo
import soporte.notificaciones.Notificacion

class PerfilController {

    def index() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        MiembroEquipo miembro = organizacion.obtenerMiembro(session.emailMiembro)
        render(view: "index", model: [miembro: miembro])
    }

    def irAdministrarOrganizacion() {
        redirect(controller: "adminOrganizacion")
    }

    def desconectar() {
        session.invalidate()
        redirect(controller: "ingresar")
    }
}
