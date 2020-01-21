package soporte

import grails.gorm.transactions.Transactional

@Transactional
class OrganizacionService {

    def crear(nombre) {
        Organizacion organizacion = new Organizacion(nombre)
    }

    def actualizarPlan(nombre, nombrePlanOferta) {
        def planOferta = PlanOferta.findByNombre(nombrePlanOferta)
        Organizacion organizacion = Organizacion.findByNombre(nombre)
        organizacion.adquirirPlan(planOferta)
        organizacion.save(failOnError: true)
    }
}
