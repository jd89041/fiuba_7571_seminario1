package soporte.reglas.respuesta

class MinimaCantidadOcurrenciasTema extends ReglaRespuesta {

    int ocurrencias

    static constraints = {
        ocurrencias min: 0
    }

    @Override
    def obtenerNombre() {
        "Mínima cantidad de ocurrencias de temas"
    }

    @Override
    def obtenerDescripcion() {
        "Define la mínima cantidad de ocurrencias de un tema en los mensajes para obtener una respuesta relacionada con el mismo"
    }

    @Override
    def procesar(pedidoSoporte, mensajes, temasDeRespuesta) {
        def temasDisponibles = []
        pedidoSoporte.ocurrenciasDeTemas.each {
            tema, ocurrenciasTema ->
                if (ocurrenciasTema >= ocurrencias)
                    temasDisponibles.add(tema)
        }
        if (temasDisponibles == 0)
            [[], []]
        else {
            def nuevosMensajes = obtenerMensajesDeTemas(mensajes, temasDisponibles)
            def nuevosTemas = temasDeRespuesta.findAll {
                it.nombre in temasDisponibles
            }
            [nuevosMensajes, nuevosTemas]
        }
    }
}
