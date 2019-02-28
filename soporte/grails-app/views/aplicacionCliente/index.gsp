<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="aplicacion.titulo"/></title>
	</head>
	<body>
	    <h1>Administración de Aplicaciones Cliente</h1>
        <g:each in="${aplicaciones}">
            <p>Nombre: ${it.nombre}</p>
            <p>Bots: ${it.herramientaBots}</p>
            <g:form>
                <g:hiddenField type="text"  name="organizacion" value="${organizacion.nombre}"/>
                <g:hiddenField type="text"  name="nombre" value="${it.nombre}"/>
                <g:actionSubmit value="Pedidos de Soporte" action="verPedidosSoporte"/>
                <g:actionSubmit value="Preguntas Frecuentes" action="verPreguntasFrecuentes"/>
            </g:form>
        </g:each>
        <br>
        <g:form>
            <g:hiddenField type="text"  name="organizacion" value="${organizacion.nombre}"/>
            <g:actionSubmit value="Agregar" action="agregarAplicacionCliente"/>
        </g:form>
	</body>
</html>