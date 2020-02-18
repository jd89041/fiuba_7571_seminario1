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
            render(view: "confirmar", model: [organizacion: nombre, email: confirmacion.email])
        }
    }

    def finalizar() {
        adminOrganizacionService.crearOrganizacionConAdmin(params.organizacion, params.email, params.password)
        session.emailMiembro = params.email
        session.nombreOrganizacion = params.organizacion
        redirect(controller: "adminOrganizacion", action: "adminPlanes")
    }

    def mostrarMensaje(contenido) {
        redirect (controller: "ingresar", action: "mensajes", params: [mensaje: contenido])
    }
}