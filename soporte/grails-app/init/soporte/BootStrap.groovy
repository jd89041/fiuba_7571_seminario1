package soporte

class BootStrap {

    def init = { servletContext ->
        crearPermisos()
        crearRoles()
        crearPlanesOferta()
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

    def crearPlanesOferta() {
        crearPlanOferta("A", 2, 1, 50)
        crearPlanOferta("B", 3, 2, 100)
        crearPlanOferta("C", 5, 3, 150)
        crearPlanOferta("D", 10, 5, 300)
    }

    def crearPlanOferta(nombre, cantidadMiembros, cantidadAplicaciones, precio) {
        PlanOferta planOferta = PlanOferta.findByNombre(nombre)
        if (!planOferta) {
            planOferta = new PlanOferta(nombre, cantidadAplicaciones, cantidadMiembros, precio)
            planOferta.save(failOnError: true, insert: true, flush: true)
        }
    }
}
