package soporte

import grails.gorm.transactions.Transactional

@Transactional
class ReceptorPedidosService {
    def manejarPedido(nombreOrganizacion, nombreAplicacionCliente, mensaje) {
        Organizacion organizacion = Organizacion.findByNombre(nombreOrganizacion)
        if (!organizacion)
            return [1, "No existe la organizacion"]
        AplicacionCliente aplicacionCliente = AplicacionCliente.findByNombre(nombreAplicacionCliente)
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
