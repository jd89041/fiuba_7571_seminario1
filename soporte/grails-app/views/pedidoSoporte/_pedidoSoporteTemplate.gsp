<div class="pedidoSoporte" id="${it?.id}" style="background-color: aliceblue;">
    <p>Pedido de soporte de app: ${it.aplicacion.nombre}</p>
    <p>Pedido de soporte del autor: ${it.emailAutor}</p>
    <button type="button" onclick="mostrarConversacion(${it.id})">Ver conversación</button>
</div>