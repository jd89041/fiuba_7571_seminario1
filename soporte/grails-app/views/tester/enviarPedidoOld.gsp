<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="aplicacion.titulo"/></title>
	</head>
	<body>
	    <h1>Tester para envío de pedido de soporte (OLD)</h1>
        <g:form controller="tester">
            Organización <br>
            <g:field type="text" name="organizacion" required=""/> <br>
            Aplicación Cliente <br>
            <g:field type="text" name="aplicacionCliente" required=""/> <br>
            Mensaje <br>
            <g:field type="text" name="mensaje" required=""/> <br>
            <g:actionSubmit value="Enviar" action="enviarPedidoSoporteOld"/>
        </g:form>
	</body>
</html>