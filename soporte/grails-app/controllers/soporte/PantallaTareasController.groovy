package soporte

class PantallaTareasController {

    def index() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        MiembroEquipo miembro = organizacion.obtenerMiembro(session.emailMiembro)
        [miembro: miembro]
    }
}
