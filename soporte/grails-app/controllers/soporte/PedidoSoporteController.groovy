package soporte

import grails.gorm.transactions.Transactional

class PedidoSoporteController {
    static responseFormats = ['json']

    def generarRespuesta(nombreAplicacion, idPedido) {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        AplicacionCliente aplicacion = organizacion.obtenerAplicacion(nombreAplicacion)
        MiembroEquipo miembro = organizacion.obtenerMiembro(session.emailMiembro)
        def conversacionPedido = organizacion.obtenerConversacionPedidoSoporteDeAplicacion(idPedido, nombreAplicacion)
        def html = g.render(template: "conversacionPedidoSoporteTemplate", model: [conversacion: conversacionPedido,
                                                                                   idPedido: idPedido,
                                                                                   nombreAplicacion: nombreAplicacion,
                                                                                   puedeResponder: miembro.estaTrabajandoEnPedidoSoporteConId(idPedido),
                                                                                   puedeAsignar: miembro.rol.tienePermiso(Permiso.TOTAL),
                                                                                   miembrosAplicacion: aplicacion.miembros])
        respond ([htmlConversacion: html], status: 200)
    }

    @Transactional
    def obtenerConversacion() {
        def nombreAplicacion = params.nombreAplicacion
        def idPedido = params.idPedido as int
        generarRespuesta(nombreAplicacion, idPedido)
    }

    @Transactional
    def enviarRespuesta() {
        def nombreAplicacion = params.nombreAplicacion
        def idPedido = params.idPedido as int
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        MiembroEquipo miembro = organizacion.obtenerMiembro(session.emailMiembro)
        def pedido = miembro.obtenerPedidoSoporteConId(idPedido)
        pedido.agregarMensaje([nombreAutor: miembro.email, contenido: params.respuesta], true)
        generarRespuesta(nombreAplicacion, idPedido)
    }
}
