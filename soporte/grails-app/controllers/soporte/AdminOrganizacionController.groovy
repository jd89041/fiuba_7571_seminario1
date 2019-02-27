package soporte

class AdminOrganizacionController {

    def organizacionService
    def confirmacionAltaMiembroService
    def adminOrganizacionService
    def rolService

    def index() {
    }

    def adminMiembros() {
        [miembros: organizacionService.obtenerMiembros(params.organizacion)]
    }

    def invitarMiembro() {
        if (organizacionService.obtener(params.organizacion).puedeInvitarMiembros())
            [roles: rolService.listar()]
        else
            mostrarMensaje("El plan actual no admite más miembros!! Por favor, actualícelo a uno superior")
    }

    def enviarInvitacion() {
        def organizacion = params.organizacion
        def email = params.email
        switch (confirmacionAltaMiembroService.obtenerOperacion(organizacion, email)) {
            case ConfirmacionAltaOrganizacion.OPERACION_ENVIAR:
                confirmacionAltaMiembroService.enviar(organizacion, email, params.rol)
                mostrarMensaje("Se envió el mail de registro")
                break
            case ConfirmacionAltaOrganizacion.OPERACION_REENVIAR:
                mostrarMensaje("Ya se envió una invitación de registro a ese email")
                break
            case ConfirmacionAltaOrganizacion.OPERACION_NO_DISPONIBLE:
                mostrarMensaje("Ese email no está disponible")
                break
        }
    }

    def confirmarInvitacion() {
        def organizacion = params.organizacion
        def email = params.email
        def ticket = params.ticket
        if (!organizacionService.existe(organizacion) || !confirmacionAltaMiembroService.esValida(organizacion, email, ticket))
            mostrarMensaje("Error: El link con la invitación para unirse a la organización caducó")
        else {
            ConfirmacionAltaMiembro confirmacion = confirmacionAltaMiembroService.obtener(organizacion, email)
            render(view: "confirmar", model: [organizacion: organizacion, email: confirmacion.email, rol: confirmacion.rol])
        }
    }

    def finalizar() {
        adminOrganizacionService.agregarMiembroEquipo(params.organizacion, params.email, params.password, params.rol)
        mostrarMensaje("Se agregó el miembro exitosamente")
    }

    def adminPlanes() {
        Organizacion organizacion = organizacionService.obtener(params.organizacion)
        def planActual = organizacion.plan
        [
            organizacion: organizacion,
            planActual: planActual,
            planes: organizacion.obtenerPlanesDisponibles()
        ]
    }

    def comprarPlan() {
        def plan = Plan.findByNombre(params.plan)
        render(view: "confirmarCompraPlan", model: [organizacion: params.organizacion, plan: plan])
    }

    def confirmarCompraPlan() {
        organizacionService.actualizarPlan(params.organizacion, params.plan)
        mostrarMensaje("El plan fue actualizado correctamente")
    }

    def adminAplicacionesCliente() {

    }

    def mostrarMensaje(contenido) {
        render (view: "/mensajes", model: [mensaje: contenido])
    }
}
