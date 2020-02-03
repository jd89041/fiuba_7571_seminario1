package soporte

class TesterController {

    def testerService
    def receptorPedidosService

    def enviarPedidoSoporte() {
        render receptorPedidosService.recibirPedido(params.organizacion, params.aplicacionCliente, params.email, params.mensaje, params.nombre)
    }

    def crearOrganizacion() {
        testerService.crearOrganizacionConAdminYAplicacion(params.organizacion, params.aplicacionCliente, params.email, params.password)
        redirect (controller: "index")
    }
}
