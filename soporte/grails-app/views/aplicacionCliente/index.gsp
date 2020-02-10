<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
	    <h1>Administración de Aplicaciones Cliente</h1>
        <g:each in="${aplicaciones}">
            <p>Nombre: ${it.nombre}</p>
            <p> Auto etiquetado de mensajes
                <g:checkBox name="" value="${it.autoEtiquetar}" disabled="disabled"/>
            </p>
            <p> Auto asignación de mensajes
                <g:checkBox name="" value="${it.autoAsignar}" disabled="disabled"/>
            </p>
            <p> Auto responder mensajes
                <g:checkBox name="" value="${it.autoResponder}" disabled="disabled"/>
            </p>
            <g:form>
                <g:hiddenField type="text"  name="nombreAplicacion" value="${it.nombre}"/>
                <g:actionSubmit value="Pedidos de Soporte" action="verPedidosSoporte"/>
                <g:actionSubmit value="Ver Temas" action="verTemas"/>
                <g:actionSubmit value="GestionarMiembros" action="gestionarMiembros"/>
            </g:form>
        </g:each>
        <g:form>
            <g:actionSubmit value="Agregar" action="agregarAplicacionCliente"/>
        </g:form>
	</body>
</html>