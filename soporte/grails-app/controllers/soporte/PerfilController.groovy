package soporte

class PerfilController {

    def miembroEquipoService

    def index() {
        MiembroEquipo miembro = miembroEquipoService.obtener(params.email)
        render(view: "index", model: [miembro: miembro])
    }

    def irAdministrarOrganizacion() {
        redirect(controller: "adminOrganizacion", params:[organizacion: params.organizacion])
    }
}
