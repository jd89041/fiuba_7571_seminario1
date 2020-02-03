package soporte.notificaciones.aplicacion

import soporte.notificaciones.Notificacion

class NotificacionAltaDeMiembroEnAplicacion extends Notificacion {
    def NotificacionAltaDeMiembroEnAplicacion(nombreAplicacion) {
        super("Se te ha dado de alta en la aplicación ${nombreAplicacion}")
    }
}
