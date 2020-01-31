package soporte

class AplicacionCliente {
    String nombre

    boolean herramientaBots // a remover!

    boolean autoEtiquetar = true
    boolean autoResolver = true
    boolean autoAsignar = true

    static belongsTo = [organizacion: Organizacion]

    static hasMany = [miembros: MiembroEquipo, pedidosSoporte: PedidoSoporte, temas: Tema]

    static mapping = {
        id generator: 'assigned', name: 'nombre'
    }

    static constraints = {
        nombre blank: false
    }

    def agregarMiembro(miembro) {
        if (!miembros)
            miembros = []
        miembros.add(miembro)
    }

    def removerMiembro(miembro) {
        miembros.remove(miembro)
    }

    def agregarTema(tema) {
        if (!temas)
            temas = []
        temas.add(tema)
    }

    def removerTema(tema) {
        temas.remove(tema)
    }

    def recuperarPedidoSoporte(email) {
        def pedido
        pedidosSoporte.each {
            if (it.emailAutor.equals(email))
                pedido = it
        }
        return pedido
    }

    // pedido de soporte recibido tiene:
    // - id del usuario con el que se trackea la historia
    // - mensaje nuevo
    def gestionarPedidoSoporteEntrante(mensajeSoporteEntrante) {
        if (!pedidosSoporte)
            pedidosSoporte = []
        String emailAutor = mensajeSoporteEntrante.emailAutor
        def pedidoSoporte = recuperarPedidoSoporte(emailAutor)
        if (!pedidoSoporte) {
            // el autor es global??
            AutorPedidoSoporte autor = AutorPedidoSoporte.findByEmail(emailAutor)
            if (!autor) {
                autor = new AutorPedidoSoporte(emailAutor)
                autor.nombre = mensajeSoporteEntrante.nombreAutor
                autor.aplicacion = mensajeSoporteEntrante.aplicacion
                // agregar otras cosas como la plataforma y el identificador de device para enviar luego al autor
                // notificaciones!!
            }
            pedidoSoporte = new PedidoSoporte(autor)
            addToPedidosSoporte(pedidoSoporte)
            autor.save(failOnError: true)
        }
        pedidoSoporte.agregarMensaje(mensajeSoporteEntrante)
        if (autoEtiquetar)
            pedidoSoporte.etiquetar()
        boolean resuelto = false
        if (autoResolver)
            resuelto = pedidoSoporte.resolver()
        boolean asignado = false
        if (autoAsignar && !resuelto) {
            // filtrar en base a reglas, luego hacer un random y asignar
            miembros.each {
                // intentar asignar en base a reglas
                if (it.asignar(pedidoSoporte))
                    return
            }
        }
        if (!asignado)
        {
            // informar como nuevo??
        }
        pedidoSoporte.save(failOnError: true)
        this.save(failOnError: true)
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
