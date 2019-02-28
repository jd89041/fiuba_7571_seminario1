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
}
