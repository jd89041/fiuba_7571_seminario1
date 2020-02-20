package soporte.notificaciones

import groovy.transform.Sortable
import soporte.MiembroEquipo
import org.joda.time.Instant

@Sortable(includes = ['fecha'])
class Notificacion {
    String mensaje
    boolean leida
    long fecha

    static belongsTo = [miembro: MiembroEquipo]

    static constraints = {
        mensaje nullable: false, blank: false
    }

    def Notificacion(mensaje) {
        setMensaje(mensaje)
        setFecha(new Instant().getMillis())
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
