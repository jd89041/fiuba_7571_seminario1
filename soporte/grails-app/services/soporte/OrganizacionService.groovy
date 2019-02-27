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

    def obtenerMiembros(nombre) {
        Organizacion organizacion = obtener(nombre)
        organizacion ? organizacion.miembros : []
    }

    def crear(nombre) {
        Organizacion organizacion = new Organizacion()
        organizacion.nombre = nombre
        organizacion.save(failOnError: true, insert: true, flush: true)
    }
}
