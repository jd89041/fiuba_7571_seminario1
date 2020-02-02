package soporte.notificaciones

class NotificacionBajaAplicacion extends Notificacion {
    def NotificacionBajaAplicacion(nombreAplicacion) {
        super("Se te ha dado de baja de la aplicación ${nombreAplicacion}")
    }
}
