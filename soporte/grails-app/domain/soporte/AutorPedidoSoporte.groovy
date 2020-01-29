package soporte

class AutorPedidoSoporte {
    String email
    String nombre
    String aplicacion

    static constraints = {
        email nullable: false, blank: false
    }

    static hasMany = [pedidosSoporte: PedidoSoporte]

    static mappings = {
        id generator: 'assigned', name: 'email'
    }

    def AutorPedidoSoporte(email) {
        this.email = email
        pedidosSoporte = []
    }

    def notificar() {
        // la idea es enviar un mensaje directamente al email indicando que tiene una actualizacion
        // en su pedido de soporte y además notificar directo a la aplicación desde la que vino el pedido
    }
}
