<h4>En curso</h4>
<g:if test="${pedidosSoporteMiembro.size() > 0}">
    <g:each in="${pedidosSoporteMiembro}">
        <g:render template="tareasAplicacionTemplate" bean="${it}"/>
    </g:each>
</g:if>
<g:else>
    No hay tareas asignadas!
</g:else>