package soporte

import grails.gorm.transactions.Transactional

@Transactional
class MiembroEquipoService {
    def confirmacionAltaMiembroService
    def confirmacionAltaOrganizacionService

    def crear(organizacion, email, password, rol) {
        MiembroEquipo miembroEquipo = new MiembroEquipo()
        miembroEquipo.organizacion = organizacion
        miembroEquipo.email = email
        miembroEquipo.password = password
        miembroEquipo.rol = rol
        miembroEquipo.save(failOnError: true, insert: true, flush: true)
    }

    def obtener(email) {
        MiembroEquipo.findByEmail(email)
    }

    def perteneceAOrganizacion(miembro, organizacion) {
        miembro.organizacion.nombre == organizacion
    }

    def credencialesValidas(miembro, password) {
        miembro.password == password
    }

    def emailUtilizado(email) {
        obtener(email) || confirmacionAltaOrganizacionService.emailRegistrado(email) || confirmacionAltaMiembroService.emailRegistrado(email)
    }
}