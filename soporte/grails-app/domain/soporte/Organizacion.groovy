package soporte

class Organizacion {

    String nombre
    //int codigoPlan

    static hasMany = [miembros: MiembroEquipo]

    static mapping = {
        id generator: 'assigned', name: 'nombre'
        secondary cascade: 'all-delete-orphan'
    }

    static constraints = {
        nombre blank: false
    }
}
