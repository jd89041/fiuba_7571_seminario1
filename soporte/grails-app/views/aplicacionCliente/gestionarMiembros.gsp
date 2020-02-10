<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
	    <h1>Gestionar miembros de ${aplicacionCliente.nombre}</h1>
        <g:if test="${aplicacionCliente.miembros.size() == 0}">
            <h1>No hay miembros asignados</h1>
        </g:if>
        <g:each in="${aplicacionCliente.miembros}">
            <p>Email: ${it.email}</p>
            <p>Password: ${it.password}</p>
            <p>Rol: ${it.describirRol()}</p>
            <g:form>
                <g:hiddenField type="text" name="nombreAplicacion" value="${aplicacionCliente.nombre}"/>
                <g:hiddenField type="text" name="emailMiembro" value="${it.email}"/>
                <g:actionSubmit value="Remover" action="removerMiembro"/>
            </g:form>
        </g:each>
        <g:if test="${miembrosInvitables.size() > 0}">
            <g:form>
                <g:hiddenField type="text" name="nombreAplicacion" value="${aplicacionCliente.nombre}"/>
                <g:select name="emailNuevoMiembro" from="${miembrosInvitables.email}" />
                <g:actionSubmit value="Agregar" action="agregarMiembro"/>
            </g:form>
        </g:if>
	</body>
</html>