package soporte

class PreguntaFrecuente {
    String pregunta
    String respuesta

    static belongsTo = [tema: Tema]

    static constraints = {
        pregunta blank: false
        respuesta blank: false
    }
}
