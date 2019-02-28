package soporte

class AplicacionCliente {
    String nombre
    boolean herramientaBots

    static belongsTo = [organizacion: Organizacion]

    static hasMany = [temas: Tema]

    static mapping = {
        id generator: 'assigned', name: 'nombre'
    }

    static constraints = {
        nombre blank: false
    }

    def obtenerRespuestaPara(mensaje) {
        def respuesta
        def mayorCantidadOcurrencias = 0
        def ocurrencias
        temas.each {
            ocurrencias = 0
            it.palabrasClave.each {
                ocurrencias += mensaje.count(it)
            }
            if (ocurrencias > mayorCantidadOcurrencias){
                mayorCantidadOcurrencias = ocurrencias
                respuesta = "${it.preguntasFrecuentes[0].pregunta}\n${it.preguntasFrecuentes[0].respuesta}"
            }
        }
        respuesta
    }
}
