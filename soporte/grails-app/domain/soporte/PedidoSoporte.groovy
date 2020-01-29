package soporte

class PedidoSoporte {

    String emailAutor

    static hasOne = [miembro: MiembroEquipo, autor: AutorPedidoSoporte]
    static hasMany = [temas: Tema, mensajes: MensajePedidoSoporte]

    static belongsTo = [aplicacion: AplicacionCliente]

    static constraints = {
        miembro nullable: true
    }

    static mapping = {
        id generator: 'assigned', name: 'emailAutor'
    }

    def PedidoSoporte(autor) {
        setAutor(autor)
        emailAutor = autor.email
        temas = []
        mensajes = []
    }

    def agregarMensaje(pedidoSoporteEntrante) {
        if (!mensajes)
            mensajes = []
        addToMensajes(new MensajePedidoSoporte(pedidoSoporteEntrante.nombreAutor, pedidoSoporteEntrante.contenido))
    }

    def taggear(temas) {
        // evalua cada mensaje preguntando a cada tema si está relacionado
    }

    def resolver() {
        // usa los temas q tiene asignados y devuelve un resultado o indica que no puede hacerlo
        true
    }

    def asignar(miembro) {
        // esta operacion deberia hacerse acá y no en el miembro equipo
        if (this.miembro)
            this.miembro.removerPedidoSoporte(this)
        this.miembro = miembro;
        this.miembro.agregarPedidoSoporte(this);
    }
}
