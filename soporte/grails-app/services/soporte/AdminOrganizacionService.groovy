package soporte

import grails.gorm.transactions.Transactional

@Transactional
class AdminOrganizacionService {
    def organizacionService
    def miembroEquipoService
    def confirmacionAltaOrganizacionService
    def confirmacionAltaMiembroService
    def rolService

    def agregarMiembroEquipo(organizacion, email, password, rol) {
        miembroEquipoService.crear(organizacionService.obtener(organizacion), email, password, rolService.obtener(rol))
        confirmacionAltaMiembroService.borrar(organizacion)
    }

    def crearOrganizacionConAdmin(organizacion, emailAdmin, passwordAdmin) {
        organizacionService.crear(organizacion)
        agregarMiembroEquipo(organizacion, emailAdmin, passwordAdmin, Rol.ADMINISTRADOR)
        //miembroEquipoService.crear(organizacionService.obtener(nombre), emailAdmin, passwordAdmin, rolService.obtener(Rol.ADMINISTRADOR))
        confirmacionAltaOrganizacionService.borrar(organizacion)
    }
}
