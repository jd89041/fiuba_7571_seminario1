package soporte

class AplicacionClienteController {

    def organizacionService
    def aplicacionClienteService

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

    def verPreguntasFrecuentes() {
        mostrarMensaje("TODO: Ver preguntas frecuentes de ${params.nombre}")
    }

    def mostrarMensaje(contenido) {
        redirect (controller: "index", action: "mensajes", params: [mensaje: contenido])
    }
}
