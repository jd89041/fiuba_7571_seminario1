package soporte.notificaciones

class NotificacionAsignacionPedidoSoporte extends Notificacion {
    def NotificacionAsignacionPedidoSoporte(nombreAplicacion) {
        super("Se te ha sido asignado un nuevo pedido de soporte de la aplicación ${nombreAplicacion}")
    }
}
