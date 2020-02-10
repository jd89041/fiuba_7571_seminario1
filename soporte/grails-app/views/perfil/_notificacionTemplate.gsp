<div class="notificacion" id="${it?.id}">
    <p>
        <div>Mensaje: ${it?.mensaje}</div>
        <div>Leida: ${it?.leida}</div>
    </p>
    <div>
    <g:if test="${!it?.leida}">
        <button type="button" onclick="leerNotificacion(${it?.id})">Leer</button>
    </g:if>
    <button type="button" onclick="borrarNotificacion(${it?.id})">Borrar :(</button>
    </div>
</div>