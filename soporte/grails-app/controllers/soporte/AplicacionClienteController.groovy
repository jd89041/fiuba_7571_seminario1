package soporte

class AplicacionClienteController {
    def testerService
    def adminOrganizacionService

    def index() {
        Organizacion organizacion = Organizacion.findByNombre(params.organizacion)
        [organizacion: organizacion, aplicaciones: organizacion.aplicaciones]
    }

    def agregarAplicacionCliente() {
        Organizacion organizacion = Organizacion.findByNombre(params.organizacion)
        if (organizacion.puedeAgregarAplicacionesCliente())
            [organizacion: organizacion]
        else
            mostrarMensaje("No se pueden agregar más aplicaciones cliente. Mejore su plan!")
    }

    def confirmarAgregar() {
        if (AplicacionCliente.findByNombre(params.nombre))
            mostrarMensaje("Ya existe la aplicación")
        else {
            adminOrganizacionService.agregarAplicacionCliente(params.organizacion, params.nombre, params.botsHabilitados)
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
        AplicacionCliente aplicacionCliente = AplicacionCliente.findByNombre(params.nombre)
        [organizacion: Organizacion.findByNombre(params.organizacion), aplicacionCliente: aplicacionCliente]
    }

    def mostrarMensaje(contenido) {
        redirect (controller: "index", action: "mensajes", params: [mensaje: contenido])
    }
}
