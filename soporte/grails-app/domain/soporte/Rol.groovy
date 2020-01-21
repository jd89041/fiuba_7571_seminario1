package soporte

class Rol {
    static final String ADMINISTRADOR = "Administrador"
    static final String AGENTE = "Agente"

    String nombre

    static hasMany = [ permisos: Permiso ]

    static mapping = {
        id generator: 'assigned', name: 'nombre'
    }

    static constraints = {
        nombre blank: false
    }

    def Rol(String rol) {
        nombre = rol ? rol : AGENTE
    }

    def getDescripcion() {
        nombre
    }

    def tienePermiso(permiso) {
        permisos.any {
            it.nombre == permiso
        }
    }
}
