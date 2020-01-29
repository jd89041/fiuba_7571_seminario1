package soporte

class MensajeSoporteEntrante {
    String aplicacion
    String emailAutor
    String nombreAutor
    String contenido

    def MensajeSoporteEntrante(contenido, aplicacion, emailAutor, nombreAutor) {
        this.contenido = contenido
        this.aplicacion = aplicacion
        this.emailAutor = emailAutor
        this.nombreAutor = nombreAutor ? nombreAutor : "Desconocido"
    }
}
