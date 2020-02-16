package soporte.reglas.ordenamiento

import soporte.reglas.Regla

abstract class ReglaOrdenamiento extends Regla {

    @Override
    def obtenerCategoría() {
        "Ordenamiento"
    }

    @Override
    def obtenerTipo() {
        "ninguno"
    }

    // reglas de ordenamiento
    // * preferencia por tema de especialidad
    // * seleccion aleatoria, el q tenga menos pedidos asignados de esta app, el q tenga menos pedidos asignados totales

    // se envía una lista y se obtiene la lista ordenada por la regla, el vector de miembros tiene que existir
    abstract def aplicar(pedidoSoporte, miembros)
}
