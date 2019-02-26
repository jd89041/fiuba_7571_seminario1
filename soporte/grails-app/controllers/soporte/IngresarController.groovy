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
        def res = ingresarService.verificarCredenciales(params)
        switch (res) {
            case CREDENCIALES_OK:
                render (view: "/mensajes", model: [mensaje: "Login correcto: ${params.email} -> ${params.password }"])
                break
            case CREDENCIALES_ERROR:
                render (view: "/mensajes", model: [mensaje: "Las credenciales no son válidas"])
                break
            case CREDENCIALES_INEXISTENTES:
                render (view: "/mensajes", model: [mensaje: "El usuario no existe en la organización ${params.organizacion}, solicite el acceso a su administrador"])
                break
        }
    }
}