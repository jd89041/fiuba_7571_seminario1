package soporte

class Tema {
    String nombre
    List<String> palabrasClave

    static belongsTo = [aplicacionCliente: AplicacionCliente]

    static hasMany = [preguntasFrecuentes : PreguntaFrecuente, respuestasAutomaticas: RespuestaAutomatica]

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

    def obtenerRespuestaAutomatica(mensaje) {
        def respuestaAutomatica
        respuestasAutomaticas.each {
            respuestaAutomatica = it.puedeResponderMensaje(mensaje)
        }
        respuestaAutomatica
    }

    def agregarRespuestaAutomatica(tituloRespuesta) {
        if (respuestasAutomaticas.any {
            it.titulo == tituloRespuesta
        })
            false
        else {
            RespuestaAutomatica nuevaRespuesta = new RespuestaAutomatica()
            nuevaRespuesta.titulo = tituloRespuesta
            addToRespuestasAutomaticas(nuevaRespuesta)
        }
    }

    def borrarRespuestaAutomatica(tituloRespuesta) {
        def respuesta = respuestasAutomaticas.find {
            it.titulo == tituloRespuesta
        }
        if (respuesta) {
            removeFromRespuestasAutomaticas(respuesta)
            respuesta.delete()
        }
    }

    def actualizarRespuestaAutomatica(tituloRespuesta, mensaje, palabrasClave) {
        def respuesta = respuestasAutomaticas.find {
            it.titulo == tituloRespuesta
        }
        if (respuesta)
            respuesta.actualizar(mensaje, palabrasClave)
        else
            false
    }

    def obtenerListaPalabrasClave() {
        def listaPalabrasClaves = ""
        palabrasClave.each { palabra ->
            listaPalabrasClaves += "[" + palabra + "]"
        }
        listaPalabrasClaves
    }

    def actualizarPalabrasClave(palabrasClave) {
        def palabras = palabrasClave.split("]")
        this.palabrasClave = []
        palabras.each {
            def palabra = it.replace("[", "")
            if (!(palabra in this.palabrasClave))
                addToPalabrasClave(palabra)
        }
        save(failOnError: true)
    }
}
