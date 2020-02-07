package soporte

class IngresarController {
    def ingresarService

    def index() {}

    def verificarOrganizacion() {
        def nombreOrganizacion = params.organizacion
        def organizacion = Organizacion.findByNombre(nombreOrganizacion)
        if (organizacion)
            render (view: "credenciales", model: [organizacion: organizacion])
        else
            render (view: "noOrganizacion", model: [organizacion: nombreOrganizacion])
    }

    def verificarCredenciales() {
        def organizacion = params.organizacion
        def email = params.email
        def password = params.password
        def res = ingresarService.verificarCredenciales(organizacion, email, password)
        switch (res) {
            case MiembroEquipo.CREDENCIALES_OK:
                session.emailMiembro = email
                session.nombreOrganizacion = organizacion
                redirect(controller: "perfil")
                break
            case MiembroEquipo.CREDENCIALES_ERROR:
                render (view: "credencialesError", model: [
                        mensaje: "Las credenciales son inválidas",
                        organizacion: organizacion,
                        email: email,
                        reenviar: true
                ])
                break
            case MiembroEquipo.CREDENCIALES_INEXISTENTES:
                render (view: "credencialesError", model: [
                        mensaje: "No existe un miembro del equipo de soporte cuyo email sea ${email}",
                        organizacion: organizacion,
                        email: email
                ])
                break
        }
    }

    def reenviarPassword() {
        def email = params.email
        ingresarService.reenviarPassword(email)
        redirect (controller: "index", action: "mensajes", params: [mensaje: "Su password fue enviado a su email"])
    }
}