package soporte.reglas.ordenamiento

class Aleatorio extends ReglaOrdenamiento {

    @Override
    def obtenerNombre() {
        "Aleatorio"
    }

    @Override
    def obtenerDescripcion() {
        "Ordena de manera aleatoria"
    }

    @Override
    def aplicar(pedidoSoporte, miembros) {
        Collection.shuffle(miembros)
    }
}
