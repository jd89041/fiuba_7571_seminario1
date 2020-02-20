<g:applyLayout name="paneles">
    <content tag="panelCentral">
        <div style="height: 40vw;">
            <div class="row align-items-center justify-content-center" style="height: 100%;">
                <div class="card text-center">
                    <div class="card-body">
                        <div class="card-title">
                            <h2>Bienvenid@</h2>
                        </div>
                        <div class="card-text">
                            <div class="row justify-content-center">
                                <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseIngresar" aria-expanded="false" aria-controls="collapseIngresar">
                                    Ingresar
                                </button>
                            </div>
                            <div class="row justify-content-center" style="padding-top: 10px;">
                                <div class="collapse" id="collapseIngresar">
                                    <div class="card card-body">
                                        <g:render template="ingresarTemplate" />
                                    </div>
                                </div>
                            </div>
                            <div class="row justify-content-center" style="padding-top: 10px;">
                                <button type="button" class="btn btn-success" onclick="mostrarCrearOrganizacionPopup()">Crear Organizacion</button>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <g:img dir="images" file="huritos.png" class="card-img"/>
                    </div>
                </div>
            </div>
        </div>
        <g:javascript>
            var pasoCrearOrganizacion;

            function mostrarCrearOrganizacionPopup() {
                pasoCrearOrganizacion = 0;
                ejecutarLlamada("adminOrganizacion/generarCrearOrganizacionPopup",
                    null,
                    function(respuesta) {
                        mostrarPopupCrearOrganizacion(respuesta.contenidoPopup);
                    }
                );
            }

            function mostrarPopupCrearOrganizacion(contenido) {
                $('#titulo-popup').html('Crear organización');
                $('#contenido-popup').html(contenido);
                mostrarPopup();
            }

            function continuarCreacionOrganizacion() {
                pasoCrearOrganizacion = 1;
                $("#nombreOrganizacionCreacion").attr("disabled", true);
                $("#collapseEmail").collapse();
            }

            function popupCancelar() {
                cerrarPopup();
            }

            function popupAceptar() {
                switch(pasoCrearOrganizacion) {
                    case 0:
                        verificarOrganizacionCreacion();
                    break;
                    case 1:
                        verificarEmailCreacion();
                    break;
                }
            }

            function verificarOrganizacionCreacion() {
                var nombreOrganizacion = $("#nombreOrganizacionCreacion").val();
                if (!nombreOrganizacion)
                    alert("Debe ingresar un nombre para continuar!");
                else {
                    ejecutarLlamada("crearOrganizacion/validarOrganizacion",
                        {
                            nombreOrganizacion: nombreOrganizacion
                        },
                        function(respuesta) {
                            switch(respuesta.codigo) {
                                case 0:
                                    continuarCreacionOrganizacion();
                                    break;
                                case 1:
                                    alert(respuesta.mensaje);
                                    break;
                                case 2:
                                    if (confirm(respuesta.mensaje))
                                        reenviarEmail();
                                    break;
                            }
                        }
                    );
                }
            }

            function verificarEmailCreacion() {
                var nombreOrganizacion = $("#nombreOrganizacionCreacion").val();
                var email = $("#emailCreacion").val();
                if (!email)
                    alert("Debe ingresar un email para continuar!");
                else {
                    ejecutarLlamada("crearOrganizacion/verificarEmail",
                        {
                            nombreOrganizacion: nombreOrganizacion,
                            email: email
                        },
                        function(respuesta) {
                            if (respuesta.codigo == 0)
                                cerrarPopup();
                            alert(respuesta.mensaje);
                        }
                    );
                }
            }
        </g:javascript>
    </content>
</g:applyLayout>
