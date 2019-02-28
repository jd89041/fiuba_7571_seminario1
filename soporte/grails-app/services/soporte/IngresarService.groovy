package soporte

import grails.gorm.transactions.Transactional

@Transactional
class IngresarService {
    def organizacionService
    def miembroEquipoService
    def mensajeroService

    def verificarCredenciales(nombreOrganizacion, email, password) {
        MiembroEquipo miembroEquipo = miembroEquipoService.obtener(email)
        Organizacion organizacion = organizacionService.obtener(nombreOrganizacion)
        if (organizacion.tieneMiembro(miembroEquipo)) {
            if (miembroEquipo.credencialesValidas(password))
                MiembroEquipo.CREDENCIALES_OK
            else
                MiembroEquipo.CREDENCIALES_ERROR
        } else
            MiembroEquipo.CREDENCIALES_INEXISTENTES
    }

    def reenviarPassword(email) {
        MiembroEquipo miembroEquipo = miembroEquipoService.obtener(email)
        def titulo = "Recordatorio de password"
        def mensaje = "Su password es: ${miembroEquipo.password}"
        mensajeroService.enviarMail(miembroEquipo.email, titulo, mensaje)
    }
}