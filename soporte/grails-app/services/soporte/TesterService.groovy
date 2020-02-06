package soporte

import grails.gorm.transactions.Transactional

@Transactional
class TesterService {

    def adminOrganizacionService
    def confirmacionAltaOrganizacionService

    def crearTemas(nombreAplicacionCliente) {
        AplicacionCliente aplicacionCliente = AplicacionCliente.findByNombre(nombreAplicacionCliente)
        Tema tema = Tema.findByAplicacionClienteAndNombre(aplicacionCliente, "login")
        if (!tema) {
            tema = new Tema()
            tema.nombre = "login"
            tema.palabrasClave = ["ingresar", "loguear", "password"]
            tema.aplicacionCliente = aplicacionCliente
            tema.save(failOnError: true, flush: true)
            crearPreguntasFrecuentes(tema, "Tengo que loguear todas las veces que recargo?", "En este prototipo si :(")
            crearPreguntasFrecuentes(tema, "Puedo recuperar el password?", "Si!! Haga click en 'Reenviar' si intenta loguear y no recuerda su password")
            crearPreguntasFrecuentes(tema, "El password puede contener numeros?", "Si, puede")
            aplicacionCliente.temas += tema
        }

        tema = Tema.findByAplicacionClienteAndNombre(aplicacionCliente, "pagos")
        if (!tema) {
            tema = new Tema()
            tema.nombre = "pagos"
            tema.palabrasClave = ["plan", "pago"]
            tema.aplicacionCliente = aplicacionCliente
            tema.save(failOnError: true, flush: true)
            crearPreguntasFrecuentes(tema, "Tengo algún plan al crear mi organización?", "No")
            crearPreguntasFrecuentes(tema, "Hay que adquirir un plan obligatoriamente?", "Si!!")
            crearPreguntasFrecuentes(tema, "Dónde puedo encontrar los planes disponibles?", "En Administración Organizacion/Administrar Planes")
            aplicacionCliente.temas += tema
        }
    }

    def crearPreguntasFrecuentes(tema, pregunta, respuesta) {
        PreguntaFrecuente preguntaFrecuente = new PreguntaFrecuente()
        preguntaFrecuente.pregunta = pregunta
        preguntaFrecuente.respuesta = respuesta
        preguntaFrecuente.tema = tema
        preguntaFrecuente.save(failOnError: true, flush: true)
    }

    def crearOrganizacionConAdminYAplicacion(nombreOrganizacion, nombreAplicacion, emailAdmin, passwordAdmin) {
        confirmacionAltaOrganizacionService.enviar(nombreOrganizacion, emailAdmin)
        adminOrganizacionService.crearOrganizacionConAdmin(nombreOrganizacion, emailAdmin, passwordAdmin)
        adminOrganizacionService.actualizarPlan(nombreOrganizacion, PlanOferta.get(3).nombre)
        adminOrganizacionService.agregarAplicacionCliente([
            nombreOrganizacion: nombreOrganizacion,
            nombreAplicacion: nombreAplicacion,
            autoEtiquetar: true,
            autoAsignar: true,
            autoResponder: true
        ])
    }
}
