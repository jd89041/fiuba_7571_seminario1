package soporte.reglas.ordenamiento

class MenosPedidosAplicacion extends ReglaOrdenamiento {

    @Override
    def obtenerNombre() {
        "Menos pedidos de soporte de la aplicación"
    }

    @Override
    def obtenerDescripcion() {
        "Ordena en base a la cantidad de pedidos de soporte de la aplicación comenzando por el menor"
    }

    @Override
    def aplicar(pedidoSoporte, miembros) {
        miembros.toSorted { a, b ->
            a.obtenerPedidosSoporteDeAplicacion(aplicacion).size() <=> b.obtenerPedidosSoporteDeAplicacion(aplicacion).size()
        }
    }
}
