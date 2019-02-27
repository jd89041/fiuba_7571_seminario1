package soporte

class IngresarController {

    static final int CREDENCIALES_OK = 0
    static final int CREDENCIALES_ERROR = 1
    static final int CREDENCIALES_INEXISTENTES = 2

    def ingresarService

    def index() {}

    def verificarOrganizacion() {
        def nombre = params.organizacion
        if (ingresarService.existeOrganizacion(nombre))
            render (view: "credenciales", model: [organizacion: nombre])
        else
            render (view: "noOrganizacion", model: [organizacion: nombre])
    }

    def verificarCredenciales() {
        def organizacion = params.organizacion
        def email = params.email
        def password = params.password
        def res = ingresarService.verificarCredenciales(organizacion, email, password)
        switch (res) {
            case CREDENCIALES_OK:
                redirect(controller: "perfil", params: [email: email])
                break
            case CREDENCIALES_ERROR:
                render (view: "credencialesError", model: [
                        mensaje: "Las credenciales son inválidas",
                        organizacion: organizacion,
                        email: email,
                        reenviar: true
                ])
                break
            case CREDENCIALES_INEXISTENTES:
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
        render (view: "/mensajes", model: [mensaje: "Su password fue enviado a su email"])
    }
}