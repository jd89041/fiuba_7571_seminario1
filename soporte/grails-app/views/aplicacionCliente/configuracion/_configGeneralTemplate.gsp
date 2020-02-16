<div class="configGeneral">
    <h4>
        General
    </h4>
    <div class="card">
        <div class="card-body">
            <g:each in="${it}">
                <p class="card-text">
                    <g:checkBox name="${it.id}" class="configGeneralCb" value="${it.activa}"/>
                    <label>${it.nombre}</label>
                </p>
            </g:each>
        </div>
    </div>
    <g:javascript>
        function obtenerConfigGeneral() {
            var config = {};
            $.each($(".configGeneralCb"), function() {
                config[$(this).attr("name")] = $(this).is(":checked");
            });
            return config;
        }
    </g:javascript>
</div>