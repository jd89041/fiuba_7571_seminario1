package soporte.reglas.respuesta

import soporte.reglas.Regla

abstract class ReglaRespuesta extends Regla {

    @Override
    def obtenerCategoría() {
        "Respuesta"
    }

    abstract def aplicar(pedidoSoporte, temasDeRespuesta)
}
