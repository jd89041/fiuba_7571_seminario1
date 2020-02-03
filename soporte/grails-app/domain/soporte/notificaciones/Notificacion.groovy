package soporte.notificaciones

import soporte.MiembroEquipo

class Notificacion {
    String mensaje
    boolean leida

    static belongsTo = [miembro: MiembroEquipo]

    static constraints = {
        mensaje nullable: false, blank: false
    }

    def Notificacion(mensaje) {
        this.mensaje = mensaje
    }

    def leer() {
        setLeida(true)
        save(failOnError: true)
    }

    def borrar() {
        delete()
    }

    def puedeEnviarseAlRol(rol) {
        true
    }
}
