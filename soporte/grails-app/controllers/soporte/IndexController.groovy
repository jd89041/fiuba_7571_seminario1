package soporte

class IndexController {
    def index() {
        def emailMiembro = session.emailMiembro
        if (emailMiembro)
            redirect(controller: "perfil", params: [email: emailMiembro])
    }

    def mensajes() {
        [mensaje: params.mensaje]
    }
}
