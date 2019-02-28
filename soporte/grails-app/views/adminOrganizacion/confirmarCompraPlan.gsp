<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="aplicacion.titulo"/></title>
	</head>
	<body>
        <h1>Confirmación de compra de plan ${plan.nombre}</h1>
        <p>Descripción</p>
        <p>Máxima cantidad de miembros: ${plan.cantidadMiembros}</p>
        <p>Máxmima cantidad de aplicaciones cliente: ${plan.cantidadAplicaciones}</p>
        <p>Costo: ${plan.costo}</p>
        <g:form>
            <g:hiddenField type="text"  name="organizacion" value="${organizacion}"/>
            <g:hiddenField type="text"  name="plan" value="${plan.nombre}"/>
            <g:actionSubmit value="Confirmar" action="confirmarCompraPlan"/>
        </g:form>
	</body>
</html>