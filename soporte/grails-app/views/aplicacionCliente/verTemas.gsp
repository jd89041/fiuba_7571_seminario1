<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="aplicacion.titulo"/></title>
	</head>
	<body>
	    <h1>Ver temas</h1>
	    <h1>${aplicacionCliente.nombre}</h1>
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
        <br>
        <g:form>
            <g:hiddenField type="text"  name="organizacion" value="${organizacion.nombre}"/>
            <g:hiddenField type="text"  name="aplicacionCliente" value="${aplicacionCliente.nombre}"/>
            <g:actionSubmit value="Agregar Tests" action="crearTemasTest"/>
        </g:form>
	</body>
</html>