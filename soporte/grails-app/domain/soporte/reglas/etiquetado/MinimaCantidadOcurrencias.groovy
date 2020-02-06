package soporte.reglas.etiquetado

class MinimaCantidadOcurrencias extends ReglaEtiquetado {
    int cantidad

    static constraints = {
        cantidad min: 0
    }

    @Override
    def obtenerNombre() {
        "Mínima cantidad de ocurrencias"
    }

    @Override
    def obtenerDescripcion() {
        "Determina la mínima cantidad de ocurrencias de una palabra para etiquetar"
    }

    @Override
    def cumple(etiqueta, ocurrencias) {
        ocurrencias >= cantidad
    }
}
