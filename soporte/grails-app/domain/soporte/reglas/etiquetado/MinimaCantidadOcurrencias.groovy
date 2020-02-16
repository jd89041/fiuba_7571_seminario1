package soporte.reglas.etiquetado

import soporte.IReglaNumerica

class MinimaCantidadOcurrencias extends ReglaEtiquetado implements IReglaNumerica {

    static final String NOMBRE = "Mínima cantidad de ocurrencias"

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
        "Determina la mínima cantidad de ocurrencias de una palabra para etiquetar"
    }

    @Override
    def cumple(etiqueta, ocurrencias) {
        ocurrencias >= cantidad
    }
}
