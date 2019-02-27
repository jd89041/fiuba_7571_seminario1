package soporte

class MiembroEquipo {

    String email
    String password
    Rol rol

    static belongsTo = [organizacion: Organizacion]

    static mapping = {
       id generator: 'assigned', name: 'email'
    }

    static constraints = {
        email blank: false
        password blank: false
        rol nullable: false
    }

    def describirRol() {
        rol.getDescripcion()
    }

    def tienePermiso(permiso) {
        rol.tienePermiso(permiso)
    }
}
