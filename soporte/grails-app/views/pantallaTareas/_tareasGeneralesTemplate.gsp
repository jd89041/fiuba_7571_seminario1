<h4>Generales</h4>
<g:if test="${pedidosSoporteGenerales.size() == 0}">
    No hay tareas!
</g:if>
<g:each in="${pedidosSoporteGenerales}">
    <g:render template="tareasAplicacionTemplate" bean="${it}"/>
</g:each>