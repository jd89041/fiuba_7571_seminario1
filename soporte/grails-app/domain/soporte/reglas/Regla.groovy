package soporte.reglas

import soporte.AplicacionCliente

abstract class Regla {

    static belongsTo = [aplicacion:AplicacionCliente]

    abstract def obtenerCategoría()
    abstract def obtenerNombre()
    abstract def obtenerDescripcion()
}
