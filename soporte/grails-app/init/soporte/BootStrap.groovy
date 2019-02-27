package soporte

class BootStrap {

    def init = { servletContext ->
        crearPermisos()
        crearRoles()
    }

    def destroy = {
    }

    def crearPermisos() {
        [ "Total", "Perfil", "PedidosSoporte"].each {
            Permiso permiso = Permiso.findByNombre(it)
            if (!permiso) {
                permiso = new Permiso()
                permiso.nombre = it
                permiso.save(flush:true)
            }
        }
    }

    def crearRoles() {
        crearRol(Rol.ADMINISTRADOR, [Permiso.findByNombre("Total")])
        crearRol(Rol.AGENTE, [Permiso.findByNombre("Perfil"), Permiso.findByNombre("PedidosSoporte")])
    }

    def crearRol(nombre, permisos) {
        Rol rol = Rol.findByNombre(nombre)
        if (!rol) {
            rol = new Rol()
            rol.nombre = nombre
            rol.permisos = permisos
            rol.save(failOnError: true, insert: true, flush: true)
        }
    }
}
