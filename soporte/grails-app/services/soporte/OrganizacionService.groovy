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

    /*
    def borrar(nombre) {
        Organizacion.findByNombre(nombre).delete()
    }
    */

    def crear(nombre) {
        Organizacion organizacion = new Organizacion()
        organizacion.nombre = nombre
        organizacion.save(failOnError: true, insert: true, flush: true)
    }
}
