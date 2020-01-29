<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="aplicacion.titulo"/></title>
	</head>
	<body>
	    <h1>Tester</h1>
        <a href="${createLink(controller: 'tests', action: 'crearOrganizacion')}">Crear Organizacion</a> <br>
        <a href="${createLink(controller: 'tests', action: 'enviarPedidoOld')}">Enviar Pedido Old</a> <br>
        <a href="${createLink(controller: 'tests', action: 'enviarPedido')}">Enviar Pedido</a> <br>
	</body>
</html>