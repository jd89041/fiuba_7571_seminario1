package soporte

import grails.gorm.transactions.Transactional

class PedidoSoporteController {
    static responseFormats = ['json']

    def generarRespuesta(pedido) {
        def html = g.render(template: "conversacionPedidoSoporteTemplate", bean: pedido)
        respond ([htmlConversacion: html], status: 200)
    }

    @Transactional
    def obtenerConversacion() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        MiembroEquipo miembro = organizacion.obtenerMiembro(session.emailMiembro)
        def pedido = miembro.obtenerPedidoSoporteConId(params.id as int)
        generarRespuesta(pedido)
    }

    @Transactional
    def enviarRespuesta() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        MiembroEquipo miembro = organizacion.obtenerMiembro(session.emailMiembro)
        def pedido = miembro.obtenerPedidoSoporteConId(params.id as int)
        pedido.agregarMensaje([nombreAutor: "obtener", contenido: params.respuesta], true)
        generarRespuesta(pedido)
    }
}
