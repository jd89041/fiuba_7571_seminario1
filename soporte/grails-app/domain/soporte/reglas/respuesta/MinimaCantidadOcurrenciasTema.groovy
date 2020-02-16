package soporte.reglas.respuesta

import soporte.IReglaNumerica

class MinimaCantidadOcurrenciasTema extends ReglaRespuesta implements IReglaNumerica {

    public static String NOMBRE = "Mínima cantidad de ocurrencias de temas"

    int ocurrencias

    static constraints = {
        ocurrencias min: 0
    }

    int obtenerValor() {
        ocurrencias
    }

    void setValor(int valor) {
        setOcurrencias(valor)
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
