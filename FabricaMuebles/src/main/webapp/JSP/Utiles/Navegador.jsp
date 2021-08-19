<%-- 
    Document   : navegador
    Created on : 15 ago. 2021, 0:23:14
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
                <a class="nav-link" href="/FabricaMuebles/Inicio.jsp">Inicio <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/ControladorProductos?tarea=mostrar">Productos <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Sobre nosotros</a>
            </li>

        </ul>
        <a class="btn btn-outline-success my-2 my-sm-0" href="/FabricaMuebles/LoginRegister.jsp">Login</a>
    </div>
</nav>
