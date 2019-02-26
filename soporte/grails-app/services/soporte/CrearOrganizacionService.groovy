package soporte

import grails.gorm.transactions.Transactional

@Transactional
class CrearOrganizacionService {

    def organizacionService
    def miembroEquipoService
    def confirmacionAltaOrganizacionService

    def crearOrganizacionConAdmin(nombre, emailAdmin, passwordAdmin) {
        organizacionService.crear(nombre)
        miembroEquipoService.crear(organizacionService.obtener(nombre), emailAdmin, passwordAdmin, true)
        confirmacionAltaOrganizacionService.borrar(nombre)
    }
}