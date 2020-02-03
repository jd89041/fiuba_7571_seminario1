package soporte.notificaciones

class NotificacionRemocionPedidoSoporte extends Notificacion {
    def NotificacionRemocionPedidoSoporte(nombreAplicacion) {
        super("Un pedido de soporte de la aplicacion ${nombreAplicacion} fue removido de tus asignaciones")
    }
}
