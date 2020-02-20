package soporte

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper

class AplicacionClienteController {
    def testerService
    def adminOrganizacionService
    def reglaService

    def index() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        [organizacion: organizacion, aplicaciones: organizacion.aplicaciones]
    }

    // borrar
    def agregarAplicacionCliente() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        if (organizacion.puedeAgregarAplicacionesCliente())
            [organizacion: organizacion]
        else
            mostrarMensaje("No se pueden agregar más aplicaciones cliente. Mejore su plan!")
    }

    // borrar
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

    def validarCreacionDeAplicacion() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        def respuesta = []
        if (organizacion.puedeAgregarAplicacionesCliente()) {
            AplicacionCliente aplicacionCliente = organizacion.obtenerAplicacion(params.nombreAplicacion)
            respuesta = [existe: aplicacionCliente != null, nombreNuevaAplicacion: params.nombreAplicacion]
        } else
            respuesta = [limiteAlcanzado: true]
        respond(respuesta, status: 200, formats: ['json'])
    }

    @Transactional
    def crearAplicacion() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        AplicacionCliente aplicacionCliente = new AplicacionCliente()
        aplicacionCliente.nombre = params.nombreAplicacion
        organizacion.agregarAplicacion(aplicacionCliente)
        respond([redirect: 'index' ], status: 200, formats: ['json'])
    }

    def obtenerConfiguracion() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        AplicacionCliente aplicacionCliente = organizacion.obtenerAplicacion(params.nombreAplicacion)
        def reglas = reglaService.obtenerTodasLasReglas(aplicacionCliente.reglas)
        def configGeneral = aplicacionCliente.obtenerConfigGeneral()
        def temas = aplicacionCliente.obtenerTemas()
        def htmlConfiguracion = g.render(template: "configuracion/configuracionTemplate", model: [
            reglas: reglas,
            configGeneral: configGeneral,
            nombreAplicacion: aplicacionCliente.nombre,
            temas: temas
        ])
        respond ([html: htmlConfiguracion], status: 200, formats: ['json'])
    }

    @Transactional
    def actualizarConfiguracion() {
        def jsonSlurper = new JsonSlurper()
        def configuracion = jsonSlurper.parseText(params.configuracion)
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        AplicacionCliente aplicacionCliente = organizacion.obtenerAplicacion(params.nombreAplicacion)
        reglaService.actualizarReglas(aplicacionCliente, configuracion.reglas)
        aplicacionCliente.actualizarConfigGeneral(configuracion.general)
        obtenerConfiguracion()
    }

    @Transactional
    def agregarTema() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        if (organizacion.agregarTemaEnAplicacion(params.nombreTema, params.nombreAplicacion))
            obtenerConfiguracion()
        else
            respond ([], status: 200, formats: ['json'])
    }

    @Transactional
    def borrarTema() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        organizacion.borrarTemaDeAplicacion(params.nombreTema, params.nombreAplicacion)
        obtenerConfiguracion()
    }

    @Transactional
    def agregarRespuestaAutomatica() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        if (organizacion.agregarRespuestaAutomatica(params.nombreAplicacion, params.nombreTema, params.tituloRespuesta))
            obtenerConfiguracion()
        else
            respond ([], status: 200, formats: ['json'])
    }

    @Transactional
    def borrarRespuestaAutomatica() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        organizacion.borrarRespuestaAutomatica(params.nombreAplicacion, params.nombreTema, params.tituloRespuesta)
        obtenerConfiguracion()
    }

    @Transactional
    def actualizarRespuestaAutomatica() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        if (organizacion.actualizarRespuestaAutomatica(params.nombreAplicacion, params.nombreTema,
                params.tituloRespuesta, params.mensaje, params.palabrasClave as String))
            obtenerConfiguracion()
        else
            respond ([], status: 200, formats: ['json'])
    }

    @Transactional
    def actualizarPalabrasClave() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        if (organizacion.actualizarPalabrasClave(params.nombreAplicacion, params.nombreTema, params.palabrasClave as String))
            obtenerConfiguracion()
        else
            respond ([], status: 200, formats: ['json'])
    }

    def mostrarMensaje(contenido) {
        redirect (controller: "ingresar", action: "mensajes", params: [mensaje: contenido])
    }
}
