package soporte

import grails.gorm.transactions.Transactional

@Transactional
class MiembroEquipoService {
    def confirmacionAltaMiembroService
    def confirmacionAltaOrganizacionService

    def emailUtilizado(email) {
        MiembroEquipo.findByEmail(email) || confirmacionAltaOrganizacionService.emailRegistrado(email) || confirmacionAltaMiembroService.emailRegistrado(email)
    }
}