<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="aplicacion.titulo"/></title>
	</head>
	<body>
	    <h1>Administración de planes</h1>
	    <g:if test="${planActual == NULL}">
	        <h1>Actualmente no posee un plan</h1>
	    </g:if>
	    <g:else>
	        <h1>Plan actual: ${planActual.nombre}</h1>
	        <h1>Cantidad de miembros: ${organizacion.miembros.size()}/${planActual.cantidadMiembros}</h1>
	        <h1>Cantidad de aplicaciones: ${organizacion.aplicacionesCliente.size()}/${planActual.cantidadAplicaciones}</h1>
	    </g:else>
	    <g:if test="${planes.size() > 0}">
	        <br>
            <h1>Planes disponibles</h1>
            <br>
        </g:if>
	    <g:each in="${planes}">
            <p>Nombre: ${it.nombre}</p>
            <p>Cantidad de miembros: ${it.cantidadMiembros}</p>
            <p>Cantidad de aplicaciones: ${it.cantidadAplicaciones}</p>
            <p>Costo: ${it.costo}</p>
            <g:form>
                <g:hiddenField type="text"  name="organizacion" value="${organizacion.nombre}"/>
                <g:hiddenField type="text"  name="plan" value="${it.nombre}"/>
                <g:actionSubmit value="Comprar" action="comprarPlan"/>
            </g:form>
            <br>
        </g:each>
	</body>
</html>