package soporte

import grails.gorm.transactions.Transactional

import grails.web.mapping.LinkGenerator

@Transactional
class ConfirmacionAltaOrganizacionService {

    def mensajeroService
    def miembroEquipoService

    LinkGenerator grailsLinkGenerator

    def existe(nombreOrganizacion) {
        ConfirmacionAltaOrganizacion.exists(nombreOrganizacion)
    }

    def obtener(nombreOrganizacion) {
        ConfirmacionAltaOrganizacion.findByNombreOrganizacion(nombreOrganizacion)
    }

    def esValida(nombreOrganizacion, ticket) {
        def confirmacionAlta = obtener(nombreOrganizacion)
        confirmacionAlta && confirmacionAlta.ticket == ticket
    }

    def obtenerOperacion(nombreOrganizacion, email) {
        int resultado = ConfirmacionAltaOrganizacion.OPERACION_ENVIAR
        ConfirmacionAltaOrganizacion confirmacion = obtener(nombreOrganizacion)
        if (confirmacion && confirmacion.email == email)
            resultado = ConfirmacionAltaOrganizacion.OPERACION_REENVIAR
        else if (ConfirmacionAltaOrganizacion.findByEmail(email) || miembroEquipoService.obtener(email))
            resultado = ConfirmacionAltaOrganizacion.OPERACION_NO_DISPONIBLE
        resultado
    }

    def enviar(nombreOrganizacion, email) {
        ConfirmacionAltaOrganizacion confirmacion = obtener(nombreOrganizacion)
        if (!confirmacion) {
            confirmacion = new ConfirmacionAltaOrganizacion()
            confirmacion.nombreOrganizacion = nombreOrganizacion
            confirmacion.email = email
            confirmacion.save(failOnError: true, insert: true, flush: true)
        }
        if (email && confirmacion.email != email)
            confirmacion.email = email
        confirmacion.ticket = generarTicket()
        procesarEnvio(confirmacion)
    }

    def procesarEnvio(confirmacion) {
        def linkCreacion = grailsLinkGenerator.link(
                controller: 'crearOrganizacion',
                action: 'confirmar',
                params: [
                        organizacion: confirmacion.nombreOrganizacion,
                        ticket: confirmacion.ticket ],
                absolute: true)
        println linkCreacion
        // hacerlo async
        mensajeroService.enviarMail(confirmacion.email,
                "Solicitud de creacion de organización",
                "Para continuar haga click aquí: ${linkCreacion}"
        )
    }

    def borrar(nombreOrganizacion) {
        ConfirmacionAltaOrganizacion.findByNombreOrganizacion(nombreOrganizacion).delete()
    }

    def generarTicket() {
        System.currentTimeMillis()
    }
}