package soporte.reglas.respuesta

import soporte.IReglaNumerica

class UltimosNMensajes extends ReglaRespuesta implements IReglaNumerica {

    static final String NOMBRE = "Ultimos N mensajes"

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
        "Define los últimos N mensajes que se toman en cuenta para buscar la respuesta automatizada"
    }

    @Override
    def procesar(pedidoSoporte, mensajes, temasDeRespuesta) {
        if (cantidad == 0)
            [mensajes, temasDeRespuesta]
        else {
            def nuevosMensajes = mensajes.takeRight(cantidad)
            def nuevosTemas = []
            nuevosMensajes.each {
                ocurrenciasTema = mensaje.obtenerOcurrenciasDeTemas(temasDeRespuesta)
                ocurrenciasTema.each { tema, ocurrencias ->
                    if (!(tema in nuevosTemas))
                        nuevosTemas.add(tema)
                }
            }
            [nuevosMensajes, nuevosTemas]
        }
    }
}
