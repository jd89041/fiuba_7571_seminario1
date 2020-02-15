package soporte

import grails.gorm.transactions.Transactional

class AplicacionClienteController {
    def testerService
    def adminOrganizacionService

    def index() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        [organizacion: organizacion, aplicaciones: organizacion.aplicaciones]
    }

    def agregarAplicacionCliente() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        if (organizacion.puedeAgregarAplicacionesCliente())
            [organizacion: organizacion]
        else
            mostrarMensaje("No se pueden agregar más aplicaciones cliente. Mejore su plan!")
    }

    def confirmarAgregar() {
        if (AplicacionCliente.findByNombre(params.nombreAplicacion))
            mostrarMensaje("Ya existe la aplicación")
        else {
            params.nombreOrganizacion = session.nombreOrganizacion
            adminOrganizacionService.agregarAplicacionCliente(params)
            mostrarMensaje("Se creó la aplicacion ${params.nombreAplicacion}")
        }
    }

    def verPedidosSoporte() {
        mostrarMensaje("TODO: Pedidos soporte de ${params.nombreAplicacion}")
    }

    def crearTemasTest() {
        testerService.crearTemas(params.nombreAplicacion)
        mostrarMensaje("Se crearon temas de prueba para la aplicacion cliente")
    }

    def verTemas() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        AplicacionCliente aplicacionCliente = organizacion.obtenerAplicacion(params.nombreAplicacion)
        [organizacion: organizacion, aplicacionCliente: aplicacionCliente]
    }

    def obtenerMiembros() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        AplicacionCliente aplicacionCliente = organizacion.obtenerAplicacion(params.nombreAplicacion)
        def miembrosInvitables = organizacion.miembros.minus(aplicacionCliente.miembros)
        def htmlMiembros = g.render(template: "/miembros/admin/miembrosAplicacionTemplate", model: [
                activos: aplicacionCliente.miembros,
                invitables: miembrosInvitables
        ])
        respond ([html: htmlMiembros], status: 200, formats: ['json'])
    }

    @Transactional
    def agregarMiembro() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        AplicacionCliente aplicacionCliente = organizacion.obtenerAplicacion(params.nombreAplicacion)
        aplicacionCliente.agregarMiembroConEmail(params.emailNuevoMiembro)
        obtenerMiembros()
    }

    @Transactional
    def removerMiembro() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        AplicacionCliente aplicacionCliente = organizacion.obtenerAplicacion(params.nombreAplicacion)
        aplicacionCliente.removerMiembroConEmail(params.emailMiembro)
        obtenerMiembros()
    }

    def mostrarMensaje(contenido) {
        redirect (controller: "ingresar", action: "mensajes", params: [mensaje: contenido])
    }
}
