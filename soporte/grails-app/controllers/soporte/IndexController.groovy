package soporte

class IndexController {
    def index() {
        def emailUsuario = session["emailUsuario"]
        if (emailUsuario)
            redirect(controller: "perfil", params: [email: emailUsuario])
    }

    def mensajes() {
        [mensaje: params.mensaje]
    }
}
