package soporte

import grails.gorm.transactions.Transactional

@Transactional
class IngresarService {
    def organizacionService
    def mensajeroService

    def verificarCredenciales(nombreOrganizacion, email, password) {
        MiembroEquipo miembroEquipo = MiembroEquipo.findByEmail(email)
        Organizacion organizacion = Organizacion.findByNombre(nombreOrganizacion)
        if (organizacion.tieneMiembro(miembroEquipo)) {
            if (miembroEquipo.credencialesValidas(password))
                MiembroEquipo.CREDENCIALES_OK
            else
                MiembroEquipo.CREDENCIALES_ERROR
        } else
            MiembroEquipo.CREDENCIALES_INEXISTENTES
    }

    def reenviarPassword(email) {
        MiembroEquipo miembroEquipo = MiembroEquipo.findByEmail(email)
        def titulo = "Recordatorio de password"
        def mensaje = "Su password es: ${miembroEquipo.password}"
        mensajeroService.enviarMail(miembroEquipo.email, titulo, mensaje)
    }
}