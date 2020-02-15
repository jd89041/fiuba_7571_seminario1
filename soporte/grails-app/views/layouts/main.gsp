<!doctype html>
<html lang="en" class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <title>
            <g:message code="aplicacion.titulo"/>
        </title>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

        <asset:stylesheet src="application.css"/>
        <!--<asset:javascript src="application.js"/>-->

        <asset:javascript src="jquery-2.2.0.min.js"/>
        <asset:javascript src="bootstrap/bootstrap.min.js"/>
        <!--<asset:javascript src="bootstrap/bootstrap.bundle.min.js"/>-->

        <asset:stylesheet src="bootstrap/bootstrap.min.css"/>
        <!--<asset:stylesheet src="bootstrap/bootstrap-grid.min.css"/>-->
        <!--<asset:stylesheet src="bootstrap/bootstrap-reboot.min.css"/>-->
        <g:layoutHead/>
    </head>
    <body>
        <g:if test="${session.emailMiembro}">
            <g:render template="/templates/compartidos/menuSuperiorTemplate"/>
        </g:if>
        <g:else>
            <g:if test="${request.forwardURI != '/'}">
                <meta http-equiv="Refresh" content="0; url='/'" />
            </g:if>
        </g:else>
        <div class="fondo-freyja">
            <g:layoutBody/>
        </div>
    </body>
</html>
