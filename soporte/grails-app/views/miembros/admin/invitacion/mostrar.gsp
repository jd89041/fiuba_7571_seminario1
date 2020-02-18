<g:applyLayout name="paneles">
    <content tag="panelCentral">
        <g:javascript>
            $( document ).ready(function() {
                mostrarInvitacion();
            });

            function mostrarInvitacion() {
                $('#titulo-popup').html("${tituloPopup}");
                $('#contenido-popup').html("${contenidoPopup}");
                ocultarBotonCancelar();
                mostrarPopup();
            }

            function popupAceptar() {
                var password = $("#contrasena").val();
                if (!password)
                    alert("Contraseña inválida!");
                else {
                    var rol = $("#rol").val()
                    ejecutarLlamada("${accion}",
                        {
                            nombreOrganizacion: "${nombreOrganizacion}",
                            email: "${email}",
                            password: password,
                            rol: "${rol}"
                        },
                        function(respuesta) {
                            if (respuesta.ok) {
                                alert(respuesta.mensaje);
                                window.location.href = respuesta.redirect;
                            }
                            else
                                alert("Hubo un error, reintente por favor");
                        }
                    );
                }
            }
        </g:javascript>
    </content>
</g:applyLayout>