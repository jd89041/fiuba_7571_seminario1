package soporte

import grails.gorm.transactions.Transactional

import soporte.MiembroEquipo
import soporte.notificaciones.Notificacion

class PerfilController {

    def index() {
        MiembroEquipo miembro = MiembroEquipo.findByEmail(params.email)
        render(view: "index", model: [miembro: miembro])
    }

    def irAdministrarOrganizacion() {
        redirect(controller: "adminOrganizacion", params:[organizacion: params.organizacion])
    }

    def desconectar() {
        session.invalidate()
        redirect(controller: "index")
    }

    @Transactional
    def leerNotificacion() {
        Organizacion organizacion = Organizacion.findByNombre(params.nombreOrganizacion)
        MiembroEquipo miembro = organizacion.miembros.find { it.email == params.emailMiembro }
        miembro.leerNotificacion(params.notificacionId as int)
        render(view: "index", model: [miembro: miembro])
    }

    @Transactional
    def borrarNotificacion() {
        Organizacion organizacion = Organizacion.findByNombre(params.nombreOrganizacion)
        MiembroEquipo miembro = organizacion.miembros.find { it.email == params.emailMiembro }
        miembro.borrarNotificacion(params.notificacionId as int)
        render(view: "index", model: [miembro: miembro])
    }
}
