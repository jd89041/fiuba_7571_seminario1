package soporte

class EnvioPedidoSoporteTesterController {

    def receptorPedidosService

    def index() { }

    def enviarPedido() {
        render receptorPedidosService.manejarPedido(params.organizacion, params.aplicacionCliente, params.mensaje)
    }
}
