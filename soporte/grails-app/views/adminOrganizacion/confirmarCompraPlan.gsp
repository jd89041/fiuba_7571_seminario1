<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="aplicacion.titulo"/></title>
	</head>
	<body>
        <h1>Confirmación de compra de plan ${planOferta.nombre}</h1>
        <p>Descripción</p>
        <p>Máxima cantidad de miembros: ${planOferta.cantidadMaxMiembros}</p>
        <p>Máxmima cantidad de aplicaciones cliente: ${planOferta.cantidadMaxAplicaciones}</p>
        <p>Precio: ${planOferta.precio}</p>
        <g:form>
            <g:hiddenField type="text"  name="planOferta" value="${planOferta.nombre}"/>
            <g:actionSubmit value="Confirmar" action="confirmarCompraPlan"/>
        </g:form>
	</body>
</html>