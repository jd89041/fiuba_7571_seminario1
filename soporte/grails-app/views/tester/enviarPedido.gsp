<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="aplicacion.titulo"/></title>
	</head>
	<body>
	    <h1>Tester para envío de pedido de soporte</h1>
        <g:form controller="tester">
            Organización <br>
            <g:field type="text" name="organizacion" required=""/> <br>
            Aplicación Cliente <br>
            <g:field type="text" name="aplicacionCliente" required=""/> <br>
            Mail <br>
            <g:field type="email" name="email" required=""/> <br>
            Nombre <br>
            <g:field type="text" name="nombre"/> <br>
            Mensaje <br>
            <g:field type="text" name="mensaje" required=""/> <br>
            <g:actionSubmit value="Enviar" action="enviarPedidoSoporte"/>
        </g:form>
	</body>
</html>