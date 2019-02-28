package soporte

import grails.gorm.transactions.Transactional

@Transactional
class ReceptorPedidosService {

    def organizacionService
    def aplicacionClienteService

    def manejarPedido(nombreOrganizacion, nombreAplicacionCliente, mensaje) {
        Organizacion organizacion = organizacionService.obtener(nombreOrganizacion)
        if (!organizacion)
            return [1, "No existe la organizacion"]
        AplicacionCliente aplicacionCliente = aplicacionClienteService.obtener(nombreAplicacionCliente)
        if (!aplicacionCliente)
            return [2, "No existe la aplicación"]
        if (!aplicacionCliente.herramientaBots)
            return [3, "Herramienta de bots deshabilitada"]
        def respuesta = aplicacionCliente.obtenerRespuestaPara(mensaje)
        respuesta ? [0, respuesta] : [4, "No hubo respuesta relacionada con el tema"]
    }

    def pedidoValido() {

    }
}
