package soporte

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        //"/"(view:"/index")    // sets the homepage of the project with grails default view
        "/"(controller:"ingresar")
        "500"(view:"/error")
        "404"(view:"/notFound")
        "/tests"(view: "/tester/index")
        "/tests/enviarPedido"(view: "/tester/enviarPedido")
        "/tests/enviarPedidoOld"(view: "/tester/enviarPedidoOld")
        "/tests/crearOrganizacion"(view: "/tester/crearOrganizacion")
    }
}
