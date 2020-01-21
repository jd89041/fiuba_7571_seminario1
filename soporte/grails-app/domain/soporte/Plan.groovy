package soporte

class Plan {
    String nombre
    int cantidadMaxAplicaciones
    int cantidadMaxMiembros
    boolean habilitado

    static belongsTo = [organizacion: Organizacion]

    static constraints = {
        nombre nullable: true
        cantidadMaxAplicaciones min: 0
        cantidadMaxMiembros min: 0
    }

    def habilitar() {
        setHabilitado(true)
    }

    def deshabilitar() {
        setHabilitado(false)
    }

    def asignado() {
        nombre != null
    }

    def estaHabilitado() {
        habilitado
    }

    def activar(PlanOferta planOferta) {
        setNombre(planOferta.nombre)
        setCantidadMaxAplicaciones(planOferta.cantidadMaxAplicaciones)
        setCantidadMaxMiembros(planOferta.cantidadMaxMiembros)
        habilitar()
    }

    def puedeAgregarMiembros(int cantMiembros) {
        habilitado && cantidadMaxMiembros > cantMiembros
    }

    def puedeAgregarAplicaciones(int cantAplicaciones) {
        habilitado && cantidadMaxAplicaciones > cantAplicaciones
    }
}
