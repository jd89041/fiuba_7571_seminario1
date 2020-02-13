<g:applyLayout name="paneles">
    <content tag="panelIzquierdo">
        <div id="izq">
        </div>
    </content>
    <content tag="panelCentral">
        <div class="container" style="height: inherit;">
            <h2>Tareas</h2>
            <div class="col-auto">
                <!-- contenedor de tareas -->
                <g:each in="${miembro.pedidosSoporte}">
                    <g:render template="/pedidoSoporte/pedidoSoporteTemplate" bean="${it}"/>
                </g:each>
            </div>
        </div>
        <g:javascript>
            function cerrarHistorial() {
                ocultarPanelDerecho();
            }

            function mostrarConversacion(id) {
                $.ajax(
                    {
                        url: "/pedidoSoporte/obtenerConversacion",
                        data: { id: id }
                    })
                    .success(function(respuesta) {
                        $('#der').html(respuesta.htmlConversacion);
                        mostrarPanelDerecho();
                    }
                );
            }

            function enviarRespuesta(id, msg) {
                $.ajax(
                    {
                        url: "/pedidoSoporte/enviarRespuesta",
                        data: {
                            id: id,
                            respuesta: $("#respuestaPedidoSoporte").val()
                        }
                    })
                    .success(function(respuesta) {
                        $("#respuestaPedidoSoporte").val("")
                        $('#der').html(respuesta.htmlConversacion);
                        mostrarPanelDerecho();
                    }
                );
            }
        </g:javascript>
    </content>

    <content tag="panelDerecho">
        <div id="der" style="height: inherit;">
            // detalle pedido soporte
        </div>
    </content>
</g:applyLayout>