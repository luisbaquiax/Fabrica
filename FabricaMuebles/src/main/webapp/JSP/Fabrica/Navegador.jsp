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
                <a class="nav-link" href="/FabricaMuebles/Inicio.jsp">Ver Piezas <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/FabricaMuebles/JSP/Administrador/Productos.jsp">Ensamblar <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/FabricaMuebles/JSP/Administrador/Productos.jsp">Registrar Muebles ensamblados <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Muebles ensamblados</a>
            </li>
        </ul>
        <a class="btn btn-outline-success my-2 my-sm-0" href="/FabricaMuebles/LoginRegister.jsp">Logout</a>
    </div>
</nav>

