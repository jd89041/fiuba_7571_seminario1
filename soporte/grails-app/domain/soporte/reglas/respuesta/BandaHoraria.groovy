package soporte.reglas.respuesta

import org.joda.time.DateTime

class BandaHoraria extends ReglaRespuesta {

    int horaMinima
    int horaMaxima

    static constraints = {
        horaMinima min: 0, max: 23
        horaMaxima min: 0, max: 23
    }

    @Override
    def obtenerNombre() {
        "Banda horaria"
    }

    @Override
    def obtenerDescripcion() {
        "Define la banda horaria en la que la aplicación responde automáticamente"
    }

    @Override
    def procesar(pedidoSoporte, mensajes, temasDeRespuesta) {
        def horaActual = new DateTime().getHourOfDay()
        if (horaMinima <= horaActual && horaActual <= horaMaxima)
            [mensajes, temasDeRespuesta]
        else
            [[], []]
    }
}
