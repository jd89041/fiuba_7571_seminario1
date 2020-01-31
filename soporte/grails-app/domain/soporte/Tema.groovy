package soporte

class Tema {
    String nombre
    List<String> palabrasClave

    static belongsTo = [aplicacionCliente: AplicacionCliente]

    static hasMany = [preguntasFrecuentes : PreguntaFrecuente]

    static constraints = {
    }

    def obtenerOcurrencias(mensaje) {
        int ocurrencias = 0
        palabrasClave.each { palabraClave ->
            def palabras = mensaje.split(" ")
            def encontradas = palabras.findAll { it.replaceAll(/[^a-z]/, "") == palabraClave }
            ocurrencias += encontradas.size
        }
        ocurrencias
    }
}
