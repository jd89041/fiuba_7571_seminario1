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
        Organizacion organizacion = new Organizacion(nombre)
    }

    def actualizarPlan(nombre, nombrePlanOferta) {
        def planOferta = PlanOferta.findByNombre(nombrePlanOferta)
        Organizacion organizacion = obtener(nombre)
        organizacion.adquirirPlan(planOferta)
        //organizacion.save(failOnError: true, insert: true, flush: true)
        //organizacion.save(failOnError: true, flush: true)
    }
}
