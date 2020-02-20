package soporte

import grails.gorm.transactions.Transactional

@Transactional
class ReceptorPedidosService {

    def recibirPedido(nombreOrganizacion, nombreAplicacionCliente, emailAutor, mensaje, nombreAutor) {
        Organizacion organizacion = Organizacion.findByNombre(nombreOrganizacion)
        if (!organizacion)
            return [1, "No existe la organizacion"]
        AplicacionCliente aplicacionCliente = organizacion.aplicaciones.find { it.nombre == nombreAplicacionCliente }
        if (!aplicacionCliente)
            return [2, "No existe la aplicación"]
        MensajeSoporteEntrante mensajeEntrante = new MensajeSoporteEntrante(mensaje,
                nombreAplicacionCliente, emailAutor, nombreAutor)
        aplicacionCliente.gestionarPedidoSoporteEntrante(mensajeEntrante)
        return [0, "ok"]
    }

}