package soporte

class CrearOrganizacionController {

    def organizacionService
    def confirmacionAltaOrganizacionService
    def adminOrganizacionService

    def index() {}

    def validarOrganizacion() {
        def organizacion = params.organizacion
        if (organizacionService.existe(organizacion))
            mostrarMensaje("La organización ${organizacion} ya existe, pruebe con otra")
        else {
            ConfirmacionAltaOrganizacion confirmacionAlta = confirmacionAltaOrganizacionService.obtener(organizacion)
            if (confirmacionAlta)
                render(view: "confirmacionEnviada", model: [organizacion: organizacion, email: confirmacionAlta.email])
            else
                render(view: "ingresarEmail", model: [organizacion: organizacion])
        }
    }

    def modificarEmail() {
        render(view: "ingresarEmail", model: [organizacion: params.organizacion, modificar: true])
    }

    def verificarEmail() {
        def email = params.email
        def organizacion = params.organizacion
        switch (confirmacionAltaOrganizacionService.obtenerOperacion(organizacion, email)) {
            case ConfirmacionAltaOrganizacion.OPERACION_ENVIAR:
                enviarEmail()
                break
            case ConfirmacionAltaOrganizacion.OPERACION_REENVIAR:
                render(view: "ingresarEmail", model: [organizacion: organizacion, repetido: true])
                break
            case ConfirmacionAltaOrganizacion.OPERACION_NO_DISPONIBLE:
                render(view: "ingresarEmail", model: [organizacion: organizacion, registrado: true])
                break
        }
    }

    def enviarEmail() {
        def organizacion = params.organizacion
        def email = params.email
        confirmacionAltaOrganizacionService.enviar(organizacion, email)
        mostrarMensaje("Se envió una solicitud al mail indicado para crear la organización ${organizacion}!")
    }

    def confirmar() {
        def nombre = params.organizacion
        if (organizacionService.existe(nombre) || !confirmacionAltaOrganizacionService.esValida(nombre, params.ticket))
            mostrarMensaje("Error: El link con la solicitud para crear la organización caducó")
        else {
            ConfirmacionAltaOrganizacion confirmacion = confirmacionAltaOrganizacionService.obtener(nombre)
            render(view: "confirmar", model: [organizacion: nombre, email: confirmacion.email])
        }
    }

    def finalizar() {
        adminOrganizacionService.crearOrganizacionConAdmin(params.organizacion, params.email, params.password)
        redirect(controller: "adminOrganizacion", action: "adminPlanes", params: [organizacion: params.organizacion])
    }

    def mostrarMensaje(contenido) {
        render (view: "/mensajes", model: [mensaje: contenido])
    }
}