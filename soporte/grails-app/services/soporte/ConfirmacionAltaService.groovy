package soporte

import grails.gorm.transactions.Transactional

import grails.web.mapping.LinkGenerator

@Transactional
abstract class ConfirmacionAltaService {

    def mensajeroService
    def miembroEquipoService

    LinkGenerator grailsLinkGenerator

    abstract def existe(nombreOrganizacion);

    abstract def obtener(nombreOrganizacion, email);

    abstract def generarContenidoEspecificoParaLink(contenido, confirmacion);

    abstract def emailRegistrado(email);

    abstract def borrar(nombreOrganizacion);

    abstract def generarTitulo();

    def esValida(nombreOrganizacion, email, ticket) {
        def confirmacionAlta = obtener(nombreOrganizacion, email)
        confirmacionAlta && confirmacionAlta.ticket == ticket
    }

    def obtenerOperacion(nombreOrganizacion, email) {
        int resultado = ConfirmacionAltaOrganizacion.OPERACION_ENVIAR
        def confirmacion = obtener(nombreOrganizacion, email)
        if (confirmacion && confirmacion.email == email)
            resultado = ConfirmacionAltaOrganizacion.OPERACION_REENVIAR
        else if (miembroEquipoService.emailUtilizado(email))
            resultado = ConfirmacionAltaOrganizacion.OPERACION_NO_DISPONIBLE
        resultado
    }

    def enviar(confirmacion, email) {
        if (email && confirmacion.email != email)
            confirmacion.email = email
        confirmacion.ticket = generarTicket()
        procesarEnvio(confirmacion)
    }

    def procesarEnvio(confirmacion) {
        def linkCreacion = grailsLinkGenerator.link(generarContenidoParaLink(confirmacion))
        log.info "Link de confirmación de alta: ${linkCreacion}"
        mensajeroService.enviarMail(confirmacion.email,
                generarTitulo(),
                generarMensaje(linkCreacion)
        )
    }

    def generarContenidoParaLink(confirmacion) {
        def contenido = [
                params:
                        [
                                organizacion: confirmacion.nombreOrganizacion,
                                ticket: confirmacion.ticket
                        ],
                absolute: true
        ]
        generarContenidoEspecificoParaLink(contenido, confirmacion)
    }

    def generarMensaje(link) {
        "Para continuar haga click aquí: ${link}"
    }

    def generarTicket() {
        System.currentTimeMillis()
    }
}
