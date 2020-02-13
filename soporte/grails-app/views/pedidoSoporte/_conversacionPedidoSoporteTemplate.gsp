<div class="conversacionPedidoSoporte" style="height: inherit;">
    <h2>Conversación</h2>
    <div class="container" style="background-color: aliceblue; overflow-y:scroll; height: 80%;">
        <g:each var="mensaje" in="${it.mensajes}">
            <g:render template="mensajePedidoSoporteTemplate" bean="${mensaje}"/>
        </g:each>
    </div>
    <g:form>
        <g:field onkeyup="manejarBotonEnviar()" type="text" name="respuestaPedidoSoporte"/>
        <button type="button" id="botonEnviarConversacion" onclick="enviarRespuesta(${it.id})" disabled>Enviar</button>
    </g:form>
    <button type="button" onclick="cerrarHistorial()">Cerrar</button>
</div>

<g:javascript>
    function manejarBotonEnviar() {
        if ($("#respuestaPedidoSoporte").val())
            $("#botonEnviarConversacion").attr("disabled", false);
        else
            $("#botonEnviarConversacion").attr("disabled", true);
    }
</g:javascript>