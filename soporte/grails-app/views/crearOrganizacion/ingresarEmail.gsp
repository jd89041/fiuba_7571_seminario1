<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
	    <h1><g:message code="crear.organizacion.titulo"/></h1>
        <h1><g:message code="ingresar.email.miembro"/></h1>
        <g:form>
            <g:field type="email" name="email" required=""/>
   	        <g:hiddenField name="organizacion" value="${organizacion}"/>
            <g:actionSubmit value="OK" action="verificarEmail"/>
        </g:form>
        <g:if test="${registrado}">
            <h1><g:message code="ingresar.email.miembro.usado"/></h1>
        </g:if>
        <g:if test="${repetido}">
            <h1><g:message code="ingresar.email.miembro.modificar.repetido"/></h1>
        </g:if>
	</body>
</html>