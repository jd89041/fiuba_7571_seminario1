package soporte.reglas.asignacion

class CantidadMaximaPedidosAplicacion extends ReglaAsignacion {
    int cantidad

    static constraints = {
        cantidad min: 0
    }

    @Override
    def obtenerNombre() {
        "Cantidad máxima de pedidos de soporte de la aplicación"
    }

    @Override
    def obtenerDescripcion() {
        "Limita la cantidad de asignaciones de los pedidos de soporte de la aplicación"
    }

    @Override
    def aplicar(pedidoSoporte, miembros) {
        miembros.findAll {
            cantidad >= it.obtenerPedidosSoporteDeAplicacion(aplicacion).size()
        }
    }
}
