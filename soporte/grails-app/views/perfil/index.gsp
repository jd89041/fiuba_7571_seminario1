<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="aplicacion.titulo"/></title>
	</head>
	<body>
	    <h1>Perfil</h1>
	    Email: ${miembro.email}
	    <br>
	    Rol: ${miembro.describirRol()}
	    <br>
        <g:if test="${miembro.tienePermiso('Total')}">
            <g:form>
                <g:hiddenField name="organizacion" value="${miembro.organizacion.nombre}"/>
                <g:actionSubmit value="Administración Organización" action="irAdministrarOrganizacion"/>
            </g:form>
        </g:if>
	</body>
</html>