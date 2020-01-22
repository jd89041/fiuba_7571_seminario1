package soporte

class ConfirmacionAltaOrganizacionService extends ConfirmacionAltaService {

    def confirmacionAltaMiembroService

    def enviar(nombreOrganizacion, email) {
        ConfirmacionAltaOrganizacion confirmacion = obtener(nombreOrganizacion)
        if (!confirmacion) {
            confirmacion = new ConfirmacionAltaOrganizacion()
            confirmacion.nombreOrganizacion = nombreOrganizacion
            confirmacion.email = email
            confirmacion.save(failOnError: true, insert: true, flush: true)
        }
        super.enviar(confirmacion, email)
    }

    def emailRegistrado(email) {
        super.emailRegistrado(email) || ConfirmacionAltaOrganizacion.findByEmail(email) || confirmacionAltaMiembroService.emailRegistrado(email)
    }

    def generarContenidoEspecificoParaLink(contenido, confirmacion) {
        contenido.controller = "crearOrganizacion"
        contenido.action = "confirmar"
        contenido
    }

    def generarTitulo() {
        "Solicitud de creacion de organización"
    }

    def esValida(nombreOrganizacion, ticket) {
        super.esValida(nombreOrganizacion, null, ticket)
    }

    def existe(nombreOrganizacion) {
        ConfirmacionAltaOrganizacion.exists(nombreOrganizacion)
    }

    def obtener(nombreOrganizacion) {
        obtener(nombreOrganizacion, null)
    }

    def obtener(nombreOrganizacion, email) {
        ConfirmacionAltaOrganizacion.findByNombreOrganizacion(nombreOrganizacion)
    }

    def borrar(nombreOrganizacion) {
        ConfirmacionAltaOrganizacion.findByNombreOrganizacion(nombreOrganizacion).delete()
    }
}