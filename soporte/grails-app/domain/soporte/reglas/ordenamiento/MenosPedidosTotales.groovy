package soporte.reglas.ordenamiento

class MenosPedidosTotales extends ReglaOrdenamiento {

    @Override
    def obtenerNombre() {
        "Menos pedidos de soporte totales"
    }

    @Override
    def obtenerDescripcion() {
        "Ordena en base a la cantidad de pedidos de soporte totales comenzando por el menor"
    }

    @Override
    def aplicar(pedidoSoporte, miembros) {
        miembros.toSorted {
            a, b -> a.pedidosSoporte.size() <=> b.pedidosSoporte.size()
        }
    }
}
