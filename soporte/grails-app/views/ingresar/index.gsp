<g:applyLayout name="paneles">
    <content tag="panelCentral">
        <div class="row justify-content-center tamano-padre">
            <div class="centrado align-self-center">
                <h2>Bienvenido</h2>
                <p>
                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseIngresar" aria-expanded="false" aria-controls="collapseIngresar">
                        Ingresar
                    </button>
                </p>
                <div class="collapse" id="collapseIngresar">
                    <div class="card card-body">
                        <g:render template="ingresarTemplate" />
                    </div>
                </div>
                <button type="button" class="btn btn-success" onclick="mostrarCrearOrganizacionPopup()">Crear Organizacion</button>
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
