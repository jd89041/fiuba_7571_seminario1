package soporte

import grails.gorm.transactions.Transactional

@Transactional
class OrganizacionService {

    def existe(nombre) {
        Organizacion.exists(nombre)
    }

    def obtener(nombre) {
        Organizacion.findByNombre(nombre)
    }

    def crear(nombre) {
        Organizacion organizacion = new Organizacion()
        organizacion.nombre = nombre
        organizacion.save(failOnError: true, insert: true, flush: true)
    }

    def actualizarPlan(nombre, nombrePlan) {
        def plan = Plan.findByNombre(nombrePlan)
        Organizacion organizacion = obtener(nombre)
        organizacion.plan = plan
        organizacion.save(failOnError: true, insert: true, flush: true)
    }
}
