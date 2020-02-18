package soporte

class CrearOrganizacionController {

    def confirmacionAltaOrganizacionService
    def adminOrganizacionService

    def index() {
        [organizacion: params.organizacion]
    }

    def validarOrganizacion() {
        def nombreOrganizacion = params.nombreOrganizacion
        if (Organizacion.exists(nombreOrganizacion))
            respond([codigo: 1, mensaje: "La organización ${nombreOrganizacion} ya existe, pruebe con otra"], status: 200, formats: ['json'])
        else {
            ConfirmacionAltaOrganizacion confirmacionAlta = confirmacionAltaOrganizacionService.obtener(nombreOrganizacion)
            if (confirmacionAlta)
                respond([codigo: 2, mensaje: "Una solicitud para iniciar la creación de la organizacion ${nombreOrganizacion} fue enviada a ${confirmacionAlta.email}"], status: 200, formats: ['json'])
            else
                respond([codigo: 0], status: 200, formats: ['json'])
        }
    }

    def modificarEmail() {
        render(view: "ingresarEmail", model: [organizacion: params.organizacion])
    }

    def verificarEmail() {
        def email = params.email
        def nombreOrganizacion = params.nombreOrganizacion
        def codigoRespuesta = 1
        def mensaje = ""
        switch (confirmacionAltaOrganizacionService.obtenerOperacion(nombreOrganizacion, email)) {
            case ConfirmacionAltaOrganizacion.OPERACION_ENVIAR:
                confirmacionAltaOrganizacionService.enviar(nombreOrganizacion, email)
                codigoRespuesta = 0
                mensaje = "Se envió una solicitud al mail indicado para crear la organización ${nombreOrganizacion}!"
                break
            case ConfirmacionAltaOrganizacion.OPERACION_REENVIAR:
                mensaje = "El email ingresado es el mismo. Debe ser distinto!"
                break
            case ConfirmacionAltaOrganizacion.OPERACION_NO_DISPONIBLE:
                mensaje = "El email ingresado ya se encuentra en uso"
                break
        }
        respond([codigo: codigoRespuesta, mensaje: mensaje], status: 200, formats: ['json'])
    }

    def confirmar() {
        def nombre = params.organizacion
        if (Organizacion.exists(nombre) || !confirmacionAltaOrganizacionService.esValida(nombre, params.ticket))
            mostrarMensaje("Error: El link con la solicitud para crear la organización caducó")
        else {
            ConfirmacionAltaOrganizacion confirmacion = confirmacionAltaOrganizacionService.obtener(nombre)
            def rol = Rol.findByNombre(Rol.ADMINISTRADOR)
            def contenidoPopup = g.render(template: "/miembros/admin/invitacion/contenidoPopupTemplate", model: [nombreOrganizacion: nombre,
                                                                                                                 email: confirmacion.email,
                                                                                                                 rol: rol,
                                                                                                                 mensaje: "El proceso de creación de la organización está por finalizar!"])
            render(view: "/miembros/admin/invitacion/mostrar", model: [tituloPopup: "Creación de ${nombre}",
                                                                       contenidoPopup: contenidoPopup,
                                                                       nombreOrganizacion: nombre,
                                                                       email: confirmacion.email,
                                                                       rol: rol.nombre,
                                                                       accion: "finalizar"])
        }
    }

    def finalizar() {
        adminOrganizacionService.crearOrganizacionConAdmin(params.nombreOrganizacion, params.email, params.password)
        session.emailMiembro = params.email
        session.nombreOrganizacion = params.nombreOrganizacion
        respond([ok: true, mensaje: "La organización fue creada con éxito!",redirect: "/adminOrganizacion/adminPlanes"], status: 200, formats: ['json'])
    }

    def mostrarMensaje(contenido) {
        redirect (controller: "ingresar", action: "mensajes", params: [mensaje: contenido])
    }
}