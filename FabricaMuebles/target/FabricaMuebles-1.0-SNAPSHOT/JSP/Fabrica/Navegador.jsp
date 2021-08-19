<%-- 
    Document   : Navegador
    Created on : 16 ago. 2021, 23:47:48
    Author     : luis
--%>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="#">MENU</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/FabricaControlador?tarea=ver">Modificar Piezas <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/FabricaControlador?tarea=ver">Ver Piezas <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/FabricaControlador?tarea=ensamblar">Ensamblar <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/FabricaControlador?tarea=registrarMuebles">Registrar Muebles ensamblados <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/FabricaControlador?tarea=mostrarEnsamblados">Muebles ensamblados</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><i class="fas fa-user"></i> Usuario: ${usuario.nombre}</a>
            </li>
        </ul>
        <a class="btn btn-outline-success my-2 my-sm-0" href="${pageContext.request.contextPath}/Salir?tarea=salir">Logout</a>
    </div>
</nav>

