package soporte.reglas.asignacion

import soporte.IReglaNumerica

class CantidadMaximaPedidosAplicacion extends ReglaAsignacion implements IReglaNumerica {

    static final String NOMBRE = "Cantidad máxima de pedidos de soporte de la aplicación"

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
        "Limita la cantidad de asignaciones de los pedidos de soporte de la aplicación"
    }

    @Override
    def aplicar(pedidoSoporte, miembros) {
        miembros.findAll {
            cantidad >= it.obtenerPedidosSoporteDeAplicacion(aplicacion).size()
        }
    }
}
