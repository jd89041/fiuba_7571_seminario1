package soporte

class Plan {
    String nombre
    int cantidadMiembros
    int cantidadAplicaciones
    int costo

    static constraints = {
        nombre blank: false
    }
}
