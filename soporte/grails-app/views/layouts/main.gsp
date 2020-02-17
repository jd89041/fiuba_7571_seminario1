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
        <!-- Popup -->
        <div class="modal fade" id="popup" tabindex="-1" role="dialog" aria-labelledby="titulo-popup" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="titulo-popup"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" id="contenido-popup">
                    </div>
                    <div class="row justify-content-center">
                        <div class="col-md-4">
                            <g:img dir="images" file="huritos.png" class="card-img"/>
                        </div>
                    </div>
                    <div class="modal-footer" id="footer-popup">
                        <button type="button" class="btn btn-primary" onclick="popupAceptar()">Aceptar</button>
                    </div>
                </div>
            </div>
        </div>
        <%--
        <g:else>
            <g:if test="${request.forwardURI != '/'}">
                <meta http-equiv="Refresh" content="0; url='/'" />
            </g:if>
        </g:else>
        --%>
        <div class="fondo-freyja">
            <g:layoutBody/>
        </div>
        <g:javascript>
            function ejecutarLlamada(url, parametros, callback) {
                $.ajax(
                    {
                        url: url,
                        data: parametros ? parametros : {}
                    })
                    .success(function(respuesta) {
                        callback(respuesta);
                    }
                );
            }
        </g:javascript>
    </body>
</html>
