package soporte.notificaciones.pedidos_soporte

import soporte.notificaciones.Notificacion

class NotificacionNuevoMensajeEnPedidoSoporte extends Notificacion {
    def NotificacionNuevoMensajeEnPedidoSoporte(nombreAplicacion) {
        super("Has recibido un mensaje nuevo en un pedido de soporte de la aplicación ${nombreAplicacion}")
    }
}
