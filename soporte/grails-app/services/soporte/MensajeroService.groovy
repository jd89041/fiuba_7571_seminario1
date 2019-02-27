package soporte

import grails.gorm.transactions.Transactional

@Transactional
class MensajeroService {

    //def emailsValidos = ["jd89041@gmail.com", "fitofitz@hotmail.com"]
    def emailsValidos = []
    def mailService

    def enviarMail(destino, titulo, mensaje) {
        log.debug "Enviando mail:\n" +
                "Destino: ${destino}\n" +
                "Titulo: ${titulo}\n" +
                "Mensaje: ${mensaje}"
        if (emailsValidos.contains(destino)) {
            mailService.sendMail {
                to destino
                subject titulo
                text mensaje
            }
        }
        else
            log.warn "No se va a enviar mail a ${destino}, no está disponible en la lista de emails válidos"

    }
}