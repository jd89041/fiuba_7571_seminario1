<g:applyLayout name="paneles">
    <content tag="panelCentral">
        <g:javascript>
            mostrarInvitacion();

            function mostrarInvitacion() {
                $('#titulo-popup').html("Bienvenid@ a ${nombreOrganizacion}");
                $('#contenido-popup').html("${contenidoPopup}");
                $('#popup').modal('show');
            }

            function popupAceptar() {
                var password = $("#contrasena").val();
                if (!password)
                    alert("Contraseña inválida!");
                else {
                    var rol = $("#rol").val()
                    ejecutarLlamada("confirmarInvitacion",
                        {
                            nombreOrganizacion: "${nombreOrganizacion}",
                            email: "${email}",
                            password: password,
                            rol: "${rol}"
                        },
                        function(respuesta) {
                            if (respuesta.ok) {
                                alert("Ha sido dado de alta exitosamente en la organización ${nombreOrganizacion}");
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