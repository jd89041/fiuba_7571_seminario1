package soporte.reglas.ordenamiento

class PreferenciaEspecialidad extends ReglaOrdenamiento {

    @Override
    def obtenerNombre() {
        "Preferencia de especialidad"
    }

    @Override
    def obtenerDescripcion() {
        "Ordena en base a la especialidad del miembro y las etiquetas del pedido de soporte"
    }

    @Override
    def aplicar(pedidoSoporte, miembros) {
        def resultado = []
        miembros.each {
            if (it.conoceTemasDelPedidoSoporte(pedidoSoporte) && !it.estaTrabajandoEnPedidoSoporte(pedidoSoporte))
                resultado.push(it)
            else
                resultado.leftShift(it)
        }
        resultado
    }
}
