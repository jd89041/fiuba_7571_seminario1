package soporte

import grails.gorm.transactions.Transactional

@Transactional
class MensajeroService {

    def mailService

    def enviarMail(destino, titulo, mensaje) {
        mailService.sendMail {
            to destino
            subject titulo
            text mensaje
        }
    }
}