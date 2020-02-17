<div class="miembro">
    <div class="card mb-3">
        <div class="card-body">
            <div class="row">
                <div class="col-md-2">
                    <g:img dir="images" file="avatares/avatar_hurito.png" class="card-img"/>
                </div>
                <div class="col">
                    <div class="card-body">
                        <h5 class="card-title">${it.email} (${it.describirRol()})</h5>
                    </div>
                </div>
                <div class="col-auto ml-auto">
                    <div class="row row-cols-3 align-items-center" style="height: 100%;">
                        <%--
                        <div class="col">
                            <button type="button" class="btn btn-primary" onclick="mostrarDatos()">Datos</button>
                        </div>
                        --%>
                        <g:if test="${it.email != session.emailMiembro}">
                            <div class="col">
                                <button type="button" class="btn btn-danger" onclick="remover('${it.email}')">Remover</button>
                            </div>
                        </g:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>