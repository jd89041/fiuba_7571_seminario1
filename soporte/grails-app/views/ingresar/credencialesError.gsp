<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="ingresar.titulo"/></title>
	</head>
	<body>
	    <h1><g:message code="ingresar.credenciales.descripcion"/></h1>
	    <h1>${mensaje}</h1>
	    <g:form>
            <g:hiddenField name="organizacion" value="${organizacion}"/>
	        <g:if test="${reenviar}">
                <g:hiddenField name="email" value="${email}"/>
                <g:actionSubmit value="Reenviar" action="reenviarPassword"/>
	        </g:if>
	        <g:actionSubmit value="Volver" action="verificarOrganizacion"/>
        </g:form>
	</body>
</html>