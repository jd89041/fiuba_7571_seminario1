package soporte.notificaciones

class NotificacionBienvenida extends Notificacion {
    def NotificacionBienvenida(nombreOrganizacion) {
        super("Bienvenido a ${nombreOrganizacion}")
    }
}
