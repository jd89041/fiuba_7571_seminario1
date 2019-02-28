package soporte

class Tema {
    String nombre
    List<String> palabrasClave

    static belongsTo = [aplicacionCliente: AplicacionCliente]

    static hasMany = [preguntasFrecuentes : PreguntaFrecuente]

    static constraints = {
    }
}
