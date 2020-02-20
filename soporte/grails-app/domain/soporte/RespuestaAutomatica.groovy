package soporte

class RespuestaAutomatica {
    Map<String, Integer> palabrasClave
    String titulo
    String mensaje

    static belongsTo = [tema: Tema]

    static constraints = {
        titulo nullable: false, blank: false
        mensaje nullable: true
        palabrasClave nullable: true
    }

    def obtenerListaPalabrasClave() {
        def listaPalabrasClaves = ""
        palabrasClave.each { palabra, ocurrencias ->
            listaPalabrasClaves += "[" + palabra + ":" + ocurrencias + "]"
        }
        listaPalabrasClaves
    }

    def puedeResponderMensaje(mensaje) {
        def resultado = true
        palabrasClave.each { palabraClave, ocurrencias ->
            def palabras = mensaje.split(" ")
            def encontradas = palabras.findAll { it.replaceAll(/[^a-z]/, "") == palabraClave }
            if (encontradas.size() < ocurrencias)
                resultado = false
        }
        resultado
    }

    def actualizar(mensaje, palabrasClave) {
        setMensaje(mensaje)
        setPalabrasClave ([:])
        def palabras = palabrasClave.split("]")
        palabras.each {
            def definicion = it.replace("[", "").split(":")
            this.palabrasClave[definicion[0]] = definicion[1] as int
        }
        save(failOnError: true)
    }
}
