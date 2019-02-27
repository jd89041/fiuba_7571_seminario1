package soporte

class BootStrap {

    def init = { servletContext ->
        crearPermisos()
        crearRoles()
        crearPlanes()
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

    def crearPlanes() {
        crearPlan("A", 2, 1, 50)
        crearPlan("B", 3, 2, 100)
        crearPlan("C", 5, 3, 150)
        crearPlan("D", 10, 5, 300)
    }

    def crearPlan(nombre, cantidadMiembros, cantidadAplicaciones, costo) {
        Plan plan = Plan.findByNombre(nombre)
        if (!plan) {
            plan = new Plan()
            plan.nombre = nombre
            plan.cantidadMiembros = cantidadMiembros
            plan.cantidadAplicaciones = cantidadAplicaciones
            plan.costo = costo
            plan.save(failOnError: true, insert: true, flush: true)
        }
    }
}
