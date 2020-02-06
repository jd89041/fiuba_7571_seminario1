package soporte.reglas.asignacion

class CantidadMaximaPedidosTotales extends ReglaAsignacion {
    int cantidad

    static constraints = {
        cantidad min: 0
    }

    @Override
    def obtenerNombre() {
        "Cantidad máxima de pedidos de soporte totales"
    }

    @Override
    def obtenerDescripcion() {
        "Limita la cantidad de asignaciones totales"
    }

    @Override
    def aplicar(pedidoSoporte, miembros) {
        miembros.findAll {
            cantidad >= it.pedidosSoporte.size()
        }
    }
}
