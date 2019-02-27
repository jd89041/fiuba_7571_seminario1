package soporte

import grails.gorm.transactions.Transactional

@Transactional
class AdminOrganizacionService {
    def organizacionService
    def miembroEquipoService
    def confirmacionAltaMiembroService
    def rolService
    def confirmacionAltaOrganizacionService

    def agregarMiembroEquipo(nombre, email, password, rol) {
        miembroEquipoService.crear(organizacionService.obtener(nombre), email, password, rolService.obtener(rol))
        confirmacionAltaMiembroService.borrar(nombre)
    }

    def crearOrganizacionConAdmin(nombre, emailAdmin, passwordAdmin) {
        organizacionService.crear(nombre)
        miembroEquipoService.crear(organizacionService.obtener(nombre), emailAdmin, passwordAdmin, rolService.obtener(Rol.ADMINISTRADOR))
        confirmacionAltaOrganizacionService.borrar(nombre)
    }
}
