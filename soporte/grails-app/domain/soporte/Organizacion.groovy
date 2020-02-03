package soporte

import soporte.notificaciones.NotificacionBienvenida
import soporte.notificaciones.aplicacion.NotificacionAltaAplicacion
import soporte.notificaciones.plan.NotificacionActualizacionPlan

class Organizacion {

    String nombre

    static hasOne = [plan: Plan]
    static hasMany = [miembros: MiembroEquipo, aplicaciones: AplicacionCliente]

    static mapping = {
        id generator: 'assigned', name: 'nombre'
        secondary cascade: 'all-delete-orphan'
    }

    static constraints = {
        nombre blank: false
    }

    def Organizacion(String nombre) {
        setNombre(nombre)
        setPlan(new Plan())
    }

    def obtenerPlanesDisponibles() {
        if (plan.asignado())
            PlanOferta.list().findAll() {
                it.puedeAdquirirse(plan.nombre, aplicaciones.size(), miembros.size())
            }
        else
            PlanOferta.list()
    }

    def puedeInvitarMiembros() {
        plan.puedeAgregarMiembros(miembros.size())
    }

    def puedeAgregarAplicacionesCliente() {
        plan.puedeAgregarAplicaciones(aplicaciones.size())
    }

    def tieneMiembro(miembro) {
        miembro && miembros.any {
            it.email == miembro.email
        }
    }

    def soportaPlanOferta(PlanOferta planOferta) {
        planOferta.puedeAdquirirse(aplicaciones.size(), miembros.size())
    }

    def adquirirPlan(PlanOferta planOferta) {
        plan.activar(planOferta)
        save(failOnError: true)
        miembros.each {
            it.notificar(new NotificacionActualizacionPlan(planOferta.nombre))
        }
    }

    def agregarAplicacion(AplicacionCliente aplicacion) {
        addToAplicaciones(aplicacion)
        save(failOnError: true)
        miembros.each {
            it.notificar(new NotificacionAltaAplicacion(aplicacion.nombre))
        }
    }

    def agregarMiembro(MiembroEquipo miembro) {
        addToMiembros(miembro)
        save(failOnError: true)
        miembro.notificar(new NotificacionBienvenida(nombre))
    }
}
