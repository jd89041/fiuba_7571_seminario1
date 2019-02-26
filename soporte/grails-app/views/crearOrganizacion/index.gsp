<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title><g:message code="crear.organizacion.titulo"/></title>
    </head>
    <body>
        <h1><g:message code="crear.organizacion.titulo"/></h1>
        <h1><g:message code="ingresar.nombre.organizacion"/></h1>
        <g:form>
            <g:field type="string" name="organizacion" required=""/>
            <g:actionSubmit value="OK" action="validarOrganizacion"/>
        </g:form>
        <g:if test="${error}">
            Error: ${error}!
        </g:if>
    </body>
</html>