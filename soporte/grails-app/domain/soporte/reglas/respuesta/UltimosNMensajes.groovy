package soporte.reglas.respuesta

class UltimosNMensajes extends ReglaRespuesta {

    int cantidad

    static constraints = {
        cantidad min: 0
    }

    @Override
    def obtenerNombre() {
        "Ultimos N mensajes"
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
