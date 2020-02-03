package soporte.notificaciones.pedidos_soporte

import soporte.notificaciones.Notificacion

class NotificacionAsignacionDePedidoSoporte extends Notificacion {
    def NotificacionAsignacionDePedidoSoporte(nombreAplicacion) {
        super("Se te ha sido asignado un nuevo pedido de soporte de la aplicación ${nombreAplicacion}")
    }
}
