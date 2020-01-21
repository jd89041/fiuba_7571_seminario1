package soporte

import grails.gorm.transactions.Transactional

@Transactional
class AplicacionClienteService {
    def organizacionService

    def obtener(nombre) {
        AplicacionCliente.findByNombre(nombre)
    }

    def crear(organizacion, nombre, herramientaBots) {
        AplicacionCliente aplicacionCliente = new AplicacionCliente()
        aplicacionCliente.nombre = nombre
        aplicacionCliente.herramientaBots = herramientaBots != null
        aplicacionCliente.organizacion = Organizacion.findByNombre(organizacion)
        aplicacionCliente.save(failOnError: true, insert: true, flush: true)
    }

}
