package soporte

class MiembroEquipo {

    String email
    String password
    boolean esAdmin

    static belongsTo = [organizacion: Organizacion]

    static mapping = {
       id generator: 'assigned', name: 'email'
    }

    static constraints = {
        email blank: false
        password blank: false
    }
}
