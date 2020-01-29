package soporte

class TesterController {

    def testerService
    def receptorPedidosService

    def enviarPedidoSoporteOld() {
        render receptorPedidosService.recibirPedidoOld(params.organizacion, params.aplicacionCliente, params.mensaje)
    }

    def enviarPedidoSoporte() {
        render receptorPedidosService.recibirPedido(params.organizacion, params.aplicacionCliente, params.email, params.mensaje, params.nombre)
    }

    def crearOrganizacion() {
        render testerService.crearOrganizacionConAdminYAplicacion(params.organizacion, params.aplicacionCliente, params.email, params.password)
    }
}
