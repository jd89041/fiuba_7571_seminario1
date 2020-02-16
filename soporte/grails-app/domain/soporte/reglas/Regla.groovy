package soporte.reglas

import soporte.AplicacionCliente

abstract class Regla {

    static belongsTo = [aplicacion:AplicacionCliente]

    boolean activa

    abstract def obtenerTipo()
    abstract def obtenerCategoría()
    abstract def obtenerNombre()
    abstract def obtenerDescripcion()
}
