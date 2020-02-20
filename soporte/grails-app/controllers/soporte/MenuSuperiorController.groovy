package soporte

import grails.gorm.transactions.Transactional

import soporte.MiembroEquipo
import soporte.notificaciones.Notificacion

class MenuSuperiorController {
    static responseFormats = ['json']

    def index() { }

    @Transactional
    def obtenerNotificaciones() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        MiembroEquipo miembro = organizacion.obtenerMiembro(session.emailMiembro)
        def html = g.render(template: "/perfil/notificacionTemplate", collection: miembro.obtenerNotificaciones())
        def nroNotificacionesNoLeidas = miembro.obtenerNotificacionesNoLeidas().size()
        respond ([htmlNotificaciones: html, nroNotificacionesNoLeidas: nroNotificacionesNoLeidas], status: 200)
    }

    @Transactional
    def leerNotificacion() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        MiembroEquipo miembro = organizacion.obtenerMiembro(session.emailMiembro)
        miembro.leerNotificacion(params.notificacionId as int)
        obtenerNotificaciones()
    }

    @Transactional
    def borrarNotificacion() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        MiembroEquipo miembro = organizacion.obtenerMiembro(session.emailMiembro)
        miembro.borrarNotificacion(params.notificacionId as int)
        obtenerNotificaciones()
    }
}
