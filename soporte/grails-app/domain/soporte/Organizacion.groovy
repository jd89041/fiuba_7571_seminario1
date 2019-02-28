package soporte

class Organizacion {

    String nombre
    Plan plan

    static hasMany = [miembros: MiembroEquipo, aplicacionesCliente: AplicacionCliente]

    static mapping = {
        id generator: 'assigned', name: 'nombre'
        secondary cascade: 'all-delete-orphan'
    }

    static constraints = {
        nombre blank: false
        plan nullable: true
    }

    def obtenerPlanesDisponibles() {
        if (plan)
            Plan.findAll() {
                cantidadMiembros > plan.cantidadMiembros
            }
        else
            Plan.list()
    }

    def puedeInvitarMiembros() {
        plan && plan.cantidadMiembros > miembros.size()
    }

    def puedeAgregarAplicacionesCliente() {
        plan && plan.cantidadAplicaciones > aplicacionesCliente.size()
    }
}
