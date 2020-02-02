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
        addToMiembros(miembro)
        save(failOnError: true)
    }

    def removerMiembro(miembro) {
        // remover todas las tareas que tenia asignadas relacionadas con esta app
        removeFromMiembros(miembro)
        save(failOnError: true)
    }

    def agregarTema(tema) {
        addToTemas(tema)
        // informar para que se puedan recalcular las etiquetas
    }

    def removerTema(tema) {
        removeFromTemas(tema)
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
        String emailAutor = mensajeSoporteEntrante.emailAutor
        def pedidoSoporte = recuperarPedidoSoporte(emailAutor)
        if (!pedidoSoporte) {
            AutorPedidoSoporte autor = pedidosSoporte.find { it.emailAutor == emailAutor }
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
        if (!pedidoSoporte.estaAsignado()) {
            boolean resuelto = false
            if (autoResolver)
                resuelto = pedidoSoporte.resolver()
            if (autoAsignar && !resuelto) {
                // filtrar en base a reglas, luego hacer un random y asignar
                def miembrosDisponibles = miembros.findAll {
                    it.puedeAsignarPedidoSoporte(pedidoSoporte)
                }
                // determinar a que miembros de los disponibles asignar la tarea
                if (miembrosDisponibles.size() > 0)
                    pedidoSoporte.asignar(miembrosDisponibles[0])
            }
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
