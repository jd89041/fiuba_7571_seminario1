package soporte

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

    def invitarMiembro() {
        if (Organizacion.findByNombre(session.nombreOrganizacion).puedeInvitarMiembros())
            [roles: Rol.list()]
        else
            mostrarMensaje("El plan actual no admite más miembros!! Mejore su plan")
    }

    def enviarInvitacion() {
        def nombreOrganizacion = session.nombreOrganizacion
        def organizacion = Organizacion.findByNombre(nombreOrganizacion)
        def email = params.email
        switch (confirmacionAltaMiembroService.obtenerOperacion(organizacion, email)) {
            case ConfirmacionAltaOrganizacion.OPERACION_ENVIAR:
                confirmacionAltaMiembroService.enviar(nombreOrganizacion, email, params.rol)
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
        def email = params.email
        def ticket = params.ticket
        def nombreOrganizacion = session.nombreOrganizacion
        if (!Organizacion.exists(nombreOrganizacion) || !confirmacionAltaMiembroService.esValida(nombreOrganizacion, email, ticket))
            mostrarMensaje("Error: El link con la invitación para unirse a la organización caducó")
        else {
            ConfirmacionAltaMiembro confirmacion = confirmacionAltaMiembroService.obtener(nombreOrganizacion, email)
            render(view: "confirmar", model: [organizacion: nombreOrganizacion, email: confirmacion.email, rol: confirmacion.rol])
        }
    }

    def finalizar() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        adminOrganizacionService.agregarMiembroEquipo(organizacion, params.email, params.password, params.rol)
        mostrarMensaje("Se agregó el miembro exitosamente")
    }

    def adminPlanes() {
        Organizacion organizacion = Organizacion.findByNombre(session.nombreOrganizacion)
        def planActual = organizacion.plan
        [
            organizacion: organizacion,
            planActual: planActual,
            planes: organizacion.obtenerPlanesDisponibles()
        ]
    }

    def comprarPlan() {
        def planOferta = PlanOferta.findByNombre(params.plan)
        render(view: "confirmarCompraPlan", model: [organizacion: session.nombreOrganizacion, planOferta: planOferta])
    }

    def confirmarCompraPlan() {
        // agregar gestor de transacciones acá
        adminOrganizacionService.actualizarPlan(session.nombreOrganizacion, params.planOferta)
        mostrarMensaje("El plan fue actualizado correctamente")
    }

    def adminAplicacionesCliente() {
        redirect (controller: "aplicacionCliente")
    }

    def mostrarMensaje(contenido) {
        redirect (controller: "ingresar", action: "mensajes", params: [mensaje: contenido])
    }
}
