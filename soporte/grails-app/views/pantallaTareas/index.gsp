<g:applyLayout name="paneles">
    <content tag="panelCentral">
        <h2>Tareas</h2>
        <div id="tareasEnCurso">
        </div>
        <div id="tareasGenerales">
        </div>
        <g:javascript>
            $(document).ready(function() {
                solicitarTareas();
            });

            function cerrarHistorial() {
                ocultarPanelDerecho();
            }

            function actualizarTareas(data) {
                $("#tareasEnCurso").html(data.htmlTareasEnCurso);
                $("#tareasGenerales").html(data.htmlTareasGenerales);
                cerrarHistorial();
            }

            function solicitarTareas() {
                ejecutarLlamada("pantallaTareas/solicitarTareas",
                    null,
                    function(respuesta) {
                        actualizarTareas(respuesta);
                    }
                );
            }

            function asignarPedido(nombreAplicacion, idPedido, emailNuevoMiembro) {
                if (confirm("Desea asignar el pedido actual a " + emailNuevoMiembro)) {
                    ejecutarLlamada("pantallaTareas/asignarPedidoSoporte",
                        {
                            nombreAplicacion: nombreAplicacion,
                            emailMiembro: emailNuevoMiembro,
                            idPedido: idPedido
                        },
                        function(respuesta) {
                            actualizarTareas(respuesta);
                        }
                    );
                }
            }

            function mostrarConversacion(nombreAplicacion, idPedido) {
                $.ajax(
                    {
                        url: "/pedidoSoporte/obtenerConversacion",
                        data: {
                            nombreAplicacion: nombreAplicacion,
                            idPedido: idPedido
                        }
                    })
                    .success(function(respuesta) {
                        $('#der').html(respuesta.htmlConversacion);
                        mostrarPanelDerecho();
                    }
                );
            }

            function enviarRespuesta(nombreAplicacion, idPedido) {
                $.ajax(
                    {
                        url: "/pedidoSoporte/enviarRespuesta",
                        data: {
                            nombreAplicacion: nombreAplicacion,
                            idPedido: idPedido,
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