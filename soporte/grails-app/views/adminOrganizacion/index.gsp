<g:applyLayout name="paneles">
	<content tag="panelCentral">
	    <div class="container" style="height: inherit;">
            <div class="row justify-content-center">
                <h1>Administración de ${nombreOrganizacion}</h1>
            </div>
            <div class="row align-items-center" style="height: 70%;">
                    <div class="col-4">
                        <g:render template="opcionAdministracionTemplate" model="[
                            'titulo': 'Miembros',
                            'imagen':'admin/miembros.png',
                            'controller': 'adminOrganizacion',
                            'action': 'adminMiembros'
                        ]"/>
                    </div>
                    <div class="col-4">
                        <g:render template="opcionAdministracionTemplate" model="[
                            'titulo': 'Planes',
                            'imagen':'admin/planes.png',
                            'controller': 'adminOrganizacion',
                            'action': 'adminPlanes'
                        ]"/>
                    </div>
                    <div class="col-4">
                        <g:render template="opcionAdministracionTemplate" model="[
                            'titulo': 'Aplicaciones',
                            'imagen':'admin/aplicaciones.png',
                            'controller': 'adminOrganizacion',
                            'action': 'adminAplicacionesCliente'
                        ]"/>
                    </div>
            </div>
        </div>
    </content>
</g:applyLayout>