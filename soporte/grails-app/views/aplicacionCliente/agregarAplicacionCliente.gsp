<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="aplicacion.titulo"/></title>
	</head>
	<body>
	    <h1>Agregar Aplicacion Cliente</h1>
        <g:form>
            <g:field type="text" name="nombre" required=""/> <br>
            <g:checkBox name="botsHabilitados" value="${true}"/> Bots habilitados? <br>
            <g:hiddenField type="text" name="organizacion" value="${organizacion.nombre}"/>
            <g:actionSubmit value="OK" action="confirmarAgregar"/>
        </g:form>
        <g:if test="${error}">
            <h1><g:message code="${error}"/></h1>
        </g:if>
	</body>
</html>