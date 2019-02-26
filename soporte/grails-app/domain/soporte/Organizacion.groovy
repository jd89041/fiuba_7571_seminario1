package soporte

class Organizacion {

    String nombre
    //int codigoPlan
    //long fechaModificacion

    static hasMany = [miembrosEquipo: MiembroEquipo]

    static mapping = {
        id generator: 'assigned', name: 'nombre'
        secondary cascade: 'all-delete-orphan'
    }

    static constraints = {
        nombre blank: false
    }
}
