package soporte.notificaciones

class NotificacionAltaAplicacion extends Notificacion {
    def NotificacionAltaAplicacion(nombreAplicacion) {
        super("Se te ha dado de alta en la aplicación ${nombreAplicacion}")
    }
}
