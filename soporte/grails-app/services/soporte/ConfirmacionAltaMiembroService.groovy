package soporte

class ConfirmacionAltaMiembroService extends ConfirmacionAltaService {
    def rolService

    def enviar(nombreOrganizacion, email, rol) {
        ConfirmacionAltaMiembro confirmacion = obtener(nombreOrganizacion, email)
        if (!confirmacion) {
            confirmacion = new ConfirmacionAltaMiembro()
            confirmacion.nombreOrganizacion = nombreOrganizacion
            confirmacion.email = email
            confirmacion.rol = rolService.obtener(rol)
            confirmacion.save(failOnError: true, insert: true, flush: true)
        }
        super.enviar(confirmacion, email)
    }

    def generarContenidoEspecificoParaLink(contenido, confirmacion) {
        contenido.controller = "adminOrganizacion"
        contenido.action = "confirmarInvitacion"
        contenido.params.email = confirmacion.email
        contenido.params.rol = confirmacion.rol.nombre
        contenido
    }

    def generarTitulo() {
        "Invitación para unirse"
    }

    def emailRegistrado(email) {
        ConfirmacionAltaMiembro.findByEmail(email)
    }

    def existe(nombreOrganizacion) {
        ConfirmacionAltaMiembro.exists(nombreOrganizacion)
    }

    def obtener(nombreOrganizacion, email) {
        ConfirmacionAltaMiembro.findByNombreOrganizacionAndEmail(nombreOrganizacion, email)
    }

    def borrar(nombreOrganizacion) {
        ConfirmacionAltaMiembro.findByNombreOrganizacion(nombreOrganizacion).delete()
    }
}