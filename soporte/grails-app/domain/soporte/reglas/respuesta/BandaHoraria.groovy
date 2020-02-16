package soporte.reglas.respuesta

import org.joda.time.DateTime
import soporte.IReglaRangoNumerico

class BandaHoraria extends ReglaRespuesta implements IReglaRangoNumerico {

    static final String NOMBRE = "Banda horaria"

    int horaMinima
    int horaMaxima

    static constraints = {
        horaMinima min: 0, max: 23
        horaMaxima min: 0, max: 23
    }

    int obtenerMinimo() {
        horaMinima
    }

    int obtenerMaximo() {
        horaMaxima
    }

    void setMinimo(int minimo) {
        setHoraMinima(minimo)
    }

    void setMaximo(int maximo) {
        setHoraMaxima(maximo)
    }

    @Override
    def obtenerTipo() {
        "rango_numerico"
    }

    @Override
    def obtenerNombre() {
        NOMBRE
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
