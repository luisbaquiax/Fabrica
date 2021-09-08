<%-- 
    Document   : navegador
    Created on : 23 ago. 2021, 1:24:35
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
                <a class="nav-link" href="#">Inicio <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/controladorAdmin?tarea=crearMueble"><i class="fas fa-tools mr-3"></i>Crear mueble <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/controladorAdmin?tarea=usuarios">Crear y cancelación de usuarios<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Reportes</a>
                <div class="dropdown-menu bg-gradient-warning">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/controladorReportes?tarea=reporteVentas">Reporte de ventas </a>
                    <a class="dropdown-item" href="#">Devoluciones</a>
                    <a class="dropdown-item" href="#">Ganacias</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/controladorReportes?tarea=usuarioMasVentas">Usuario que registra más ventas</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/controladorReportes?tarea=productosDisponibles">Usuario que registra más ganancias</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/controladorReportes?tarea=productosDisponibles">Mueble más vendido</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/controladorReportes?tarea=productosDisponibles">Mueble menos vendido</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><i class="fas fa-user pr-3"></i>${usuario.nombre} ${usuario.tipo}</a>
            </li>

        </ul>
        <a class="btn btn-outline-success my-2 my-sm-0" href="${pageContext.request.contextPath}/Salir">Salir</a>
    </div>
</nav>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

    if (session.getAttribute("usuario") == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
%>