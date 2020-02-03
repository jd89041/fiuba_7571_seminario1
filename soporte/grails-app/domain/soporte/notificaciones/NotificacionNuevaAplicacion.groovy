package soporte.notificaciones

class NotificacionNuevaAplicacion extends Notificacion {
    def NotificacionNuevaAplicacion(nombreAplicacion) {
        super("Se ha agregado con éxito la aplicación ${nombreAplicacion} a la organización")
    }
}
