package soporte

import grails.gorm.transactions.Transactional

@Transactional
class IngresarService {

    def organizacionService
    def miembroEquipoService

    def existeOrganizacion(nombre) {
        organizacionService.existe(nombre)
    }

    def verificarCredenciales(params) {
        int res = IngresarController.CREDENCIALES_INEXISTENTES
        MiembroEquipo miembroEquipo = miembroEquipoService.obtener(params.email)
        if (miembroEquipo) {
            if (miembroEquipoService.credencialesValidas(miembroEquipo, params.password))
                res = IngresarController.CREDENCIALES_OK
            else
                res = IngresarController.CREDENCIALES_ERROR
        }
        res
    }
}