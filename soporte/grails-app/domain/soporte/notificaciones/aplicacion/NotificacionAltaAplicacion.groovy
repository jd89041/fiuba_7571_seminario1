package soporte.notificaciones.aplicacion

import soporte.Permiso
import soporte.notificaciones.Notificacion

class NotificacionAltaAplicacion extends Notificacion {
    def NotificacionAltaAplicacion(nombreAplicacion) {
        super("Se ha agregado con éxito la aplicación ${nombreAplicacion} a la organización")
    }

    def puedeEnviarseAlRol(rol) {
        rol.tienePermiso(Permiso.TOTAL)
    }
}
