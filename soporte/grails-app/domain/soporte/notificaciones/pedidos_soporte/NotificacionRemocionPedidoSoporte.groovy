package soporte.notificaciones.pedidos_soporte

import soporte.notificaciones.Notificacion

class NotificacionRemocionPedidoSoporte extends Notificacion {
    def NotificacionRemocionPedidoSoporte(nombreAplicacion) {
        super("Un pedido de soporte de la aplicacion ${nombreAplicacion} fue removido de tus asignaciones")
    }
}
