package soporte

import grails.gorm.transactions.Transactional

@Transactional
class MiembroEquipoService {

    def crear(organizacion, email, password, esAdmin) {
        MiembroEquipo miembroEquipo = new MiembroEquipo()
        miembroEquipo.organizacion = organizacion
        miembroEquipo.email = email
        miembroEquipo.password = password
        miembroEquipo.esAdmin = esAdmin
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
}