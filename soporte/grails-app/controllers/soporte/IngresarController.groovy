package soporte

class IngresarController {
    static responseFormats = ['json']

    def ingresarService

    def index() {
        def emailMiembro = session.emailMiembro
        if (emailMiembro)
            redirect(controller: "PantallaTareas")
    }

    def verificarOrganizacion() {
        def nombreOrganizacion = params.nombreOrganizacion
        def organizacion = Organizacion.findByNombre(nombreOrganizacion)
        if (organizacion)
            response << 0
        else
            response << 1
    }

    def verificarCredenciales() {
        def organizacion = params.nombreOrganizacion
        def email = params.email
        def password = params.password
        def res = ingresarService.verificarCredenciales(organizacion, email, password)
        switch (res) {
            case MiembroEquipo.CREDENCIALES_OK:
                session.emailMiembro = email
                session.nombreOrganizacion = organizacion
                respond ([credencial: "ok", redirect: 'PantallaTareas' ], status: 200)
                break
            case MiembroEquipo.CREDENCIALES_ERROR:
                respond ([credencial: "invalida"], status: 200)
                break
            case MiembroEquipo.CREDENCIALES_INEXISTENTES:
                respond ([credencial: "inexistente"], status: 200)
                break
        }
    }

    def reenviarPassword() {
        def email = params.email
        ingresarService.reenviarPassword(email)
        redirect (controller: "ingresar", action: "mensajes", params: [mensaje: "Su password fue enviado a su email"])
    }

    def mensajes() {
        [mensaje: params.mensaje]
    }
}