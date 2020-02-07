<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="aplicacion.titulo"/></title>
	</head>
	<body>
	    <h1>Ver temas de ${aplicacionCliente.nombre}</h1>
        <g:if test="${aplicacionCliente.temas.size() == 0}">
            <h1>No hay temas</h1>
        </g:if>
        <g:each in="${aplicacionCliente.temas}">
            <p>Tema: ${it.nombre}</p>
            <p>Palabras clave: ${it.palabrasClave}</p>
            <p>Preguntas Frecuentes</p>
            <g:each in="${it.preguntasFrecuentes}">
                <p>P: ${it.pregunta}</p>
                <p>R: ${it.respuesta}</p>
            </g:each>
            <br>
        </g:each>
        <g:form>
            <g:hiddenField type="text"  name="nombreAplicacion" value="${aplicacionCliente.nombre}"/>
            <g:actionSubmit value="Agregar Tests" action="crearTemasTest"/>
        </g:form>
	</body>
</html>