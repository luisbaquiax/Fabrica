<%-- 
    Document   : navegador
    Created on : 19 ago. 2021, 17:12:23
    Author     : luis
--%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!-- para dar formato el texto-->
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- SELECCIONAMOS LA LOCALIDAD -->
<fmt:setLocale value="es_GT"/>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="#">MENU</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="#">Devoluciones <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Consultas</a>
                <div class="dropdown-menu bg-gradient-warning">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/controladorTienda?tarea=listadoClientes">Consulta de compras de algún cliente en un intervalo de tiempo </a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/controladorTienda?tarea=listadoClientes">Consulta de devoluciones realizadas por un cliente en un intervalo X de tiempo</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/controladorTienda?tarea=productosDisponibles">Consulta de muebles disponibles en la sala de ventas</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/controladorTienda?tarea=ventasDelDia&user=${usuario.nombre}">Consulta de las ventas del día</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><i class="fas fa-user"></i> Usuario: ${usuario.nombre}</a>
            </li>
        </ul>
        <a class="btn btn-outline-success my-2 my-sm-0" href="${pageContext.request.contextPath}/Salir">Logout</a>
    </div>
</nav>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

    if (session.getAttribute("usuario") == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
%>