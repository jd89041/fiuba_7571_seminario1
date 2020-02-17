<g:applyLayout name="paneles">
    <content tag="panelEncabezado">
        Administración de miembro
    </content>
    <content tag="panelCentral">
        <h3>Miembros</h3>
        <div id="contenedorMiembros">
            <g:render template="/miembros/miembrosTemplate" bean="${it}"/>
        </div>
        <g:hiddenField name="organizacion" value="${session.nombreOrganizacion}"/>
        <button type="button" class="btn btn-success" onclick="iniciarInvitacion()">Invitar</button>

        <g:javascript>
            function iniciarInvitacion() {
                ejecutarLlamada("validarInvitarMiembro",
                    null,
                    function(respuesta) {
                        if (respuesta.ok)
                            mostrarPopupAgregarMiembro(respuesta.html);
                        else
                            alert("No se pueden agregar más miembros. Mejore su plan!");
                    }
                );
            }

            function mostrarPopupAgregarMiembro(html) {
                $('#titulo-popup').html('Agregar miembro');
                $('#contenido-popup').html(html);
                $('#popup').modal('show');
            }

            function popupAceptar() {
                var email = $("#email").val();
                if (!email)
                    alert("Email inválido!");
                else {
                    var rol = $("#rol").val()
                    ejecutarLlamada("enviarInvitacion",
                        {
                            email: email,
                            rol: rol
                        },
                        function(respuesta) {
                            if (respuesta.ok) {
                                $('#popup').modal('hide');
                            }
                            alert(respuesta.mensaje);
                        }
                    );
                }
            }

            function remover(emailMiembro) {
                if (confirm("Desea remover al miembro " + emailMiembro + " de la organización?")) {
                    ejecutarLlamada("removerMiembro",
                        {
                            email: emailMiembro
                        },
                        function(respuesta) {
                            alert("El miembro se removió con éxito");
                            $("#contenedorMiembros").html(respuesta.html);
                        }
                    );
                }
            }

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
	</content>
	<content tag="panelDerecho">
	    <div id="contenidoPanelDerecho"/>
	</content>
</g:applyLayout>