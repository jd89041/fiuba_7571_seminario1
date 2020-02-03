package soporte.notificaciones.aplicacion

import soporte.notificaciones.Notificacion

class NotificacionBajaDeMiembroDeAplicacion extends Notificacion {
    def NotificacionBajaDeMiembroDeAplicacion(nombreAplicacion) {
        super("Se te ha dado de baja de la aplicación ${nombreAplicacion}")
    }
}
