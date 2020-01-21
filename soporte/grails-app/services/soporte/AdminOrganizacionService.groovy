package soporte

import grails.gorm.transactions.Transactional
import soporte.Organizacion

@Transactional
class AdminOrganizacionService {
    def confirmacionAltaOrganizacionService
    def confirmacionAltaMiembroService

    def agregarMiembroEquipo(organizacion, email, password, rol) {
        MiembroEquipo miembroEquipo = new MiembroEquipo()
        miembroEquipo.organizacion = organizacion
        miembroEquipo.email = email
        miembroEquipo.password = password
        miembroEquipo.rol = Rol.findByNombre(rol)
        organizacion.agregarMiembro(miembroEquipo)
        organizacion.save(failOnError: true)
        confirmacionAltaMiembroService.borrar(organizacion.nombre)
    }

    def crearOrganizacionConAdmin(nombreOrganizacion, emailAdmin, passwordAdmin) {
        Organizacion organizacion = new Organizacion(nombreOrganizacion)
        agregarMiembroEquipo(organizacion, emailAdmin, passwordAdmin, Rol.ADMINISTRADOR)
        organizacion.save(failOnError: true)
        confirmacionAltaOrganizacionService.borrar(organizacion.nombre)
    }
}
