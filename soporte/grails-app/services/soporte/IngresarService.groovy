package soporte

import grails.gorm.transactions.Transactional

@Transactional
class IngresarService {

    def organizacionService
    def miembroEquipoService
    def mensajeroService

    def existeOrganizacion(nombre) {
        organizacionService.existe(nombre)
    }

    def verificarCredenciales(organizacion, email, password) {
        int res = IngresarController.CREDENCIALES_INEXISTENTES
        MiembroEquipo miembroEquipo = miembroEquipoService.obtener(email)
        if (miembroEquipo && miembroEquipoService.perteneceAOrganizacion(miembroEquipo, organizacion)) {
            if (miembroEquipoService.credencialesValidas(miembroEquipo, password))
                res = IngresarController.CREDENCIALES_OK
            else
                res = IngresarController.CREDENCIALES_ERROR
        }
        res
    }

    def reenviarPassword(email) {
        MiembroEquipo miembroEquipo = miembroEquipoService.obtener(email)
        def titulo = "Recordatorio de password"
        def mensaje = "Su password es: ${miembroEquipo.password}"
        mensajeroService.enviarMail(miembroEquipo.email, titulo, mensaje)
    }
}