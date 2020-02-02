package soporte.notificaciones

class NotificacionActualizacionPlan extends Notificacion {
    def NotificacionActualizacionPlan(nombrePlan) {
        super("El plan de la organización fue actualizado con éxito. El nuevo plan es: ${nombrePlan}")
    }
}
