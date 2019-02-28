package soporte

class MiembroEquipo {

    static final int CREDENCIALES_OK = 0
    static final int CREDENCIALES_ERROR = 1
    static final int CREDENCIALES_INEXISTENTES = 2

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

    def credencialesValidas(password) {
        this.password == password
    }
}
