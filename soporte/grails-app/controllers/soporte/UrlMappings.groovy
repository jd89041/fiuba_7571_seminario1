package soporte

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        //"/"(view:"/index")    // sets the homepage of the project with grails default view
        "/"(controller:"index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
