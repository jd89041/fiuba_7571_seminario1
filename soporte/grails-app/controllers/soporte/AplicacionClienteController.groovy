package soporte

class AplicacionClienteController {

    def organizacionService
    def aplicacionClienteService
    def testerService

    def index() {
        Organizacion organizacion = organizacionService.obtener(params.organizacion)
        [organizacion: organizacion, aplicaciones: organizacion.aplicacionesCliente]
    }

    def agregarAplicacionCliente() {
        Organizacion organizacion = organizacionService.obtener(params.organizacion)
        if (organizacion.puedeAgregarAplicacionesCliente())
            [organizacion: organizacion]
        else
            mostrarMensaje("No se pueden agregar más aplicaciones cliente. Mejore su plan!")
    }

    def confirmarAgregar() {
        if (aplicacionClienteService.existe(params.nombre))
            mostrarMensaje("Ya existe la aplicación")
        else {
            aplicacionClienteService.crear(params.organizacion, params.nombre, params.botsHabilitados)
            mostrarMensaje("Se creó la aplicacion ${params.nombre}")
        }
    }

    def verPedidosSoporte() {
        mostrarMensaje("TODO: Pedidos soporte de ${params.nombre}")
    }

    def crearTemasTest() {
        testerService.crearTemas(params.aplicacionCliente)
        mostrarMensaje("Se crearon temas de prueba para la aplicacion cliente")
    }

    def verTemas() {
        AplicacionCliente aplicacionCliente = aplicacionClienteService.obtener(params.nombre)
        [organizacion: organizacionService.obtener(params.organizacion), aplicacionCliente: aplicacionCliente]
    }

    def mostrarMensaje(contenido) {
        redirect (controller: "index", action: "mensajes", params: [mensaje: contenido])
    }
}
