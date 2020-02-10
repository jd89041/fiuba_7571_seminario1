<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
	    <h1>Agregar Aplicacion Cliente</h1>
        <g:form>
            <g:field type="text" name="nombreAplicacion" required=""/> <br>
            <g:checkBox name="autoEtiquetar" value="${true}"/> Auto etiquetado de mensajes <br>
            <g:checkBox name="autoAsignar" value="${true}"/> Auto asignación de mensajes <br>
            <g:checkBox name="autoResponder" value="${true}"/> Auto responder mensajes <br>
            <g:actionSubmit value="OK" action="confirmarAgregar"/>
        </g:form>
        <g:if test="${error}">
            <h1><g:message code="${error}"/></h1>
        </g:if>
	</body>
</html>