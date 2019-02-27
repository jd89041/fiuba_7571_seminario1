<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="aplicacion.titulo"/></title>
	</head>
	<body>
        <h1>Confirmación de compra de plan</h1>
        <h1>${organizacion}</h1>
        <p>Nombre: ${plan.nombre}</p>
        <p>Cantidad de miembros: ${plan.cantidadMiembros}</p>
        <p>Cantidad de aplicaciones: ${plan.cantidadAplicaciones}</p>
        <p>Costo: ${plan.costo}</p>
        <g:form>
            <g:hiddenField type="text"  name="organizacion" value="${organizacion}"/>
            <g:hiddenField type="text"  name="plan" value="${plan.nombre}"/>
            <g:actionSubmit value="Confirmar" action="confirmarCompraPlan"/>
        </g:form>
	</body>
</html>