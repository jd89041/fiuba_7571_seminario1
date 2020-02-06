package soporte.reglas.asignacion

import soporte.reglas.Regla

abstract class ReglaAsignacion extends Regla {

    @Override
    def obtenerCategoría() {
        "Asignación"
    }

    abstract def aplicar(pedidoSoporte, miembros)

}
