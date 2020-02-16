package soporte.reglas.asignacion

import soporte.IReglaNumerica

class CantidadMaximaPedidosTotales extends ReglaAsignacion implements IReglaNumerica {

    static final String NOMBRE = "Cantidad máxima de pedidos de soporte totales"

    int cantidad

    static constraints = {
        cantidad min: 0
    }

    int obtenerValor() {
        cantidad
    }

    void setValor(int valor) {
        setCantidad(valor)
    }

    @Override
    def obtenerTipo() {
        "numeral"
    }

    @Override
    def obtenerNombre() {
        NOMBRE
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
