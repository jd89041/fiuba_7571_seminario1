package soporte

import grails.gorm.transactions.Transactional

class AdminOrganizacionController {

    def confirmacionAltaMiembroService
    def adminOrganizacionService

    def index() {
        [nombreOrganizacion: session.nombreOrganizacion]
    }

    def adminMiembros() {
        def organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        [miembros: organizacion.miembros]
    }

    def enviarInvitacion() {
        def nombreOrganizacion = session.nombreOrganizacion
        def organizacion = Organizacion.findByNombre(nombreOrganizacion)
        def email = params.email
        def ok
        def mensaje
        switch (confirmacionAltaMiembroService.obtenerOperacion(organizacion, email)) {
            case ConfirmacionAltaOrganizacion.OPERACION_ENVIAR:
                confirmacionAltaMiembroService.enviar(nombreOrganizacion, email, params.rol)
                ok = true
                mensaje = "Se envió una invitación al mail indicado"
                break
            case ConfirmacionAltaOrganizacion.OPERACION_REENVIAR:
                mensaje = "Ya se envió una invitación de registro a ese email"
                break
            case ConfirmacionAltaOrganizacion.OPERACION_NO_DISPONIBLE:
                mensaje = "Ese email no está disponible"
                break
        }
        respond ([ok: ok, mensaje: mensaje], status: 200, formats: ['json'])
    }

    def mostrarInvitacion() {
        def email = params.email
        def ticket = params.ticket
        def nombreOrganizacion = params.organizacion
        if (!Organizacion.exists(nombreOrganizacion) || !confirmacionAltaMiembroService.esValida(nombreOrganizacion, email, ticket))
            mostrarMensaje("Error: El link con la invitación para unirse a la organización caducó")
        else {
            ConfirmacionAltaMiembro confirmacion = confirmacionAltaMiembroService.obtener(nombreOrganizacion, email)
            def contenidoPopup = g.render(template: "/miembros/admin/invitacion/contenidoPopupTemplate", model: [nombreOrganizacion: nombreOrganizacion,
                                                                                                                 email: confirmacion.email,
                                                                                                                 rol: confirmacion.rol,
                                                                                                                 mensaje: "Ha sido invitado a la organización con el rol de ${confirmacion.rol.descripcion}."])
            render(view: "/miembros/admin/invitacion/mostrar", model: [tituloPopup: "Bienvenid@ a ${nombreOrganizacion}",
                                                                       contenidoPopup: contenidoPopup,
                                                                       nombreOrganizacion: nombreOrganizacion,
                                                                       email: email,
                                                                       rol: confirmacion.rol.nombre,
                                                                       accion: "confirmarInvitacion"])
        }
    }

    def confirmarInvitacion() {
        def nombreOrganizacion = params.nombreOrganizacion
        def email = params.email
        Organizacion organizacion = Organizacion.findByNombre(nombreOrganizacion)
        adminOrganizacionService.agregarMiembroEquipo(organizacion, email, params.password, params.rol, false)
        session.emailMiembro = email
        session.nombreOrganizacion = nombreOrganizacion
        respond ([ok: true, mensaje: "Ha sido dado de alta exitosamente en la organización ${nombreOrganizacion}", redirect: "/"], status: 200, formats: ['json'])
    }

    def adminPlanes() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        def planActual = organizacion.plan
        def model = [
            organizacion: organizacion,
            planActual: planActual,
            planes: organizacion.obtenerPlanesDisponibles()
        ]
        render(view: "planes/adminPlanes", model: model)
    }

    def comprarPlan() {
        def planOferta = PlanOferta.findByNombre(params.plan)
        render(view: "confirmarCompraPlan", model: [organizacion: session.nombreOrganizacion, planOferta: planOferta])
    }

    @Transactional
    def adquirirPlan() {
        // agregar gestor de transacciones acá
        adminOrganizacionService.actualizarPlan(session.nombreOrganizacion, params.plan)
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        def htmlPlanActual = g.render(template: "planes/planActualTemplate", bean: organizacion.plan)
        def htmlPlanesOferta = g.render(template: "planes/listaPlanesTemplate", bean: organizacion.obtenerPlanesDisponibles())
        respond ([html: [planActual: htmlPlanActual, planesOferta: htmlPlanesOferta]], status: 200, formats: ['json'])
    }

    def validarInvitarMiembro() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        def htmlAgregar = g.render(template: "/miembros/formAgregarMiembroTemplate")
        respond ([ok: organizacion.puedeInvitarMiembros(), html: htmlAgregar], status: 200, formats: ['json'])
    }

    @Transactional
    def removerMiembro() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        organizacion.removerMiembroConEmail(params.email)
        def htmlMiembros = g.render(template: "/miembros/miembrosTemplate", model: ["miembros": organizacion.miembros])
        respond ([html: htmlMiembros], status: 200, formats: ['json'])
    }

    def adminAplicacionesCliente() {
        redirect (controller: "aplicacionCliente")
    }

    def generarCrearOrganizacionPopup() {
        def contenidoPopup = g.render(template: "formCrearOrganizacionTemplate")
        respond ([contenidoPopup: contenidoPopup], status: 200, formats: ['json'])
    }

    def completarCrearOrganizacionPopup() {
        def contenidoPopup = g.render(template: "formCrearOrganizacionTemplate", model: [nombreOrganizacion: params.nombreOrganizacion])
        respond ([contenidoPopup: contenidoPopup], status: 200, formats: ['json'])
    }

    def mostrarMensaje(contenido) {
        redirect (controller: "ingresar", action: "mensajes", params: [mensaje: contenido])
    }
}
