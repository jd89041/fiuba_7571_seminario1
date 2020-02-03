package soporte.notificaciones.plan

import soporte.Permiso
import soporte.notificaciones.Notificacion

class NotificacionActualizacionPlan extends Notificacion {
    def NotificacionActualizacionPlan(nombrePlan) {
        super("El plan de la organización fue actualizado con éxito. El nuevo plan es: ${nombrePlan}")
    }

    def puedeEnviarseAlRol(rol) {
        rol.tienePermiso(Permiso.TOTAL)
    }
}
