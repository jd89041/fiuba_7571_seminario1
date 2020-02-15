<g:applyLayout name="paneles">
    <content tag="panelEncabezado">
        Administración de aplicaciones
    </content>
    <content tag="panelCentral">
        <h3>Aplicaciones</h3>
        <g:each in="${aplicaciones}">
            <g:render template="aplicacionTemplate" bean="${it}"/>
        </g:each>
        <g:form>
            <g:actionSubmit value="Agregar" action="agregarAplicacionCliente"/>
        </g:form>
        <g:javascript>
            function invitarMiembro(nombreAplicacion, emailMiembro) {
                ejecutarLlamada("agregarMiembro",
                    {
                        nombreAplicacion: nombreAplicacion,
                        emailNuevoMiembro: emailMiembro
                    },
                    function(respuesta) {
                        $("#contenidoPanelDerecho").html(respuesta.html);
                    }
                );
            }

            function removerMiembro(nombreAplicacion, emailMiembro) {
                ejecutarLlamada("removerMiembro",
                    {
                        nombreAplicacion: nombreAplicacion,
                        emailMiembro: emailMiembro
                    },
                    function(respuesta) {
                        $("#contenidoPanelDerecho").html(respuesta.html);
                    }
                );
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

            function mostrarMiembros(nombreAplicacion) {
                ejecutarLlamada("obtenerMiembros",
                    {
                        nombreAplicacion: nombreAplicacion
                    },
                    function(respuesta) {
                        $("#contenidoPanelDerecho").html(respuesta.html);
                        mostrarPanelDerecho();
                    }
                );
            }

            function mostrarTemas(nombreAplicacion) {
                console.log("nombreAplicacion");
            }

            function mostrarConfiguracion(nombreAplicacion) {
                console.log("nombreAplicacion");
            }
        </g:javascript>
	</content>
	<content tag="panelDerecho">
	    <div id="contenidoPanelDerecho"/>
	</content>
</g:applyLayout>