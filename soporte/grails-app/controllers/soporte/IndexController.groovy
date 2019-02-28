package soporte

class IndexController {
    def index() {}

    def mensajes() {
        [mensaje: params.mensaje]
    }
}
