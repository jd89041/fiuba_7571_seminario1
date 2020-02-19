package soporte

import grails.gorm.transactions.Transactional

class PantallaTareasController {

    def index() {}

    def solicitarTareas() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        def pedidosSoporteGenerales = organizacion.obtenerPedidosSoporteGenerales()
        def pedidosSoporteMiembro = organizacion.obtenerPedidosSoporteMiembro(session.emailMiembro)
        def htmlTareasEnCurso = g.render(template: "tareasEnCursoTemplate", model: [pedidosSoporteMiembro: pedidosSoporteMiembro])
        def htmlTareasGenerales = g.render(template: "tareasGeneralesTemplate", model: [pedidosSoporteGenerales: pedidosSoporteGenerales])
        respond([codigo: 0, htmlTareasEnCurso: htmlTareasEnCurso, htmlTareasGenerales: htmlTareasGenerales], status: 200, formats: ['json'])
    }

    @Transactional
    def asignarPedidoSoporte() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        organizacion.asignarPedidoSoporte(params.nombreAplicacion, params.idPedido as int, params.emailMiembro)
        solicitarTareas()
    }
}
