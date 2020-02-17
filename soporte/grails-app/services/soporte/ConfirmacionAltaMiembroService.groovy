package soporte

class ConfirmacionAltaMiembroService extends ConfirmacionAltaService {

    def confirmacionAltaOrganizacionService

    def enviar(nombreOrganizacion, email, rol) {
        ConfirmacionAltaMiembro confirmacion = obtener(nombreOrganizacion, email)
        if (!confirmacion) {
            confirmacion = new ConfirmacionAltaMiembro()
            confirmacion.nombreOrganizacion = nombreOrganizacion
            confirmacion.email = email
        }
        confirmacion.rol = Rol.findByNombre(rol)
        confirmacion.save(failOnError: true, insert: true, flush: true)
        super.enviar(confirmacion, email)
    }

    def generarContenidoEspecificoParaLink(contenido, confirmacion) {
        contenido.controller = "adminOrganizacion"
        contenido.action = "mostrarInvitacion"
        contenido.params.email = confirmacion.email
        contenido.params.rol = confirmacion.rol.nombre
        contenido
    }

    def generarTitulo() {
        "Invitación para unirse"
    }

    def emailRegistrado(email) {
        super.emailRegistrado(email) || ConfirmacionAltaMiembro.findByEmail(email) || confirmacionAltaOrganizacionService.emailRegistrado(email)
    }

    def existe(nombreOrganizacion) {
        // sin uso
    }

    def obtener(nombreOrganizacion, email) {
        ConfirmacionAltaMiembro.findByNombreOrganizacionAndEmail(nombreOrganizacion, email)
    }

    def borrar(nombreOrganizacion, email) {
        ConfirmacionAltaMiembro.findByNombreOrganizacionAndEmail(nombreOrganizacion, email).delete()
    }
}