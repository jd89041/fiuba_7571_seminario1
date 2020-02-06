package soporte.reglas.etiquetado

import soporte.reglas.Regla

abstract class ReglaEtiquetado extends Regla {

    @Override
    def obtenerCategoría() {
        "Etiquetado"
    }

    abstract def cumple(etiqueta, ocurrencias)

}
