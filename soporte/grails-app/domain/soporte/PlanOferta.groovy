package soporte

class PlanOferta {
    String nombre
    int cantidadMaxAplicaciones
    int cantidadMaxMiembros
    int precio

    static constraints = {
        nombre blank: false
        cantidadMaxAplicaciones min: 0
        cantidadMaxMiembros min: 0
        precio min: 0
    }

    def PlanOferta(String nombre, int cantMaxAplicaciones, int cantMaxMiembros, int precio) {
        this.nombre = nombre
        this.cantidadMaxAplicaciones = cantMaxAplicaciones
        this.cantidadMaxMiembros = cantMaxMiembros
        this.precio = precio
    }

    def puedeAdquirirse(String nombre, int cantAplicaciones, int cantMiembrosEquipo) {
        !this.nombre.equals(nombre) &&
        this.cantidadMaxAplicaciones >= cantAplicaciones &&
        this.cantidadMaxMiembros >= cantMiembrosEquipo
    }
}
