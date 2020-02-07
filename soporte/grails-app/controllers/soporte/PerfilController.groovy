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
        redirect(controller: "index")
    }

    @Transactional
    def leerNotificacion() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        MiembroEquipo miembro = organizacion.obtenerMiembro(session.emailMiembro)
        miembro.leerNotificacion(params.notificacionId as int)
        render(view: "index", model: [miembro: miembro])
    }

    @Transactional
    def borrarNotificacion() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        MiembroEquipo miembro = organizacion.obtenerMiembro(session.emailMiembro)
        miembro.borrarNotificacion(params.notificacionId as int)
        render(view: "index", model: [miembro: miembro])
    }
}
