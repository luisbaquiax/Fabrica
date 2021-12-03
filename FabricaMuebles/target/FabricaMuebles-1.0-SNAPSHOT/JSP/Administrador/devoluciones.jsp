<%-- 
    Document   : devoluciones
    Created on : 22 nov. 2021, 19:29:38
    Author     : luis
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- para dar formato el texto-->
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- SELECCIONAMOS LA LOCALIDAD -->
<fmt:setLocale value="es_GT"/>
<!doctype html>
<html lang="en">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <!-- personalizados CSS -->
        <link href="../../assets/css/general.css" rel="stylesheet" type="text/css"/>
        <link href="../../assets/css/margenes.css" rel="stylesheet" type="text/css"/>
        <!-- icons -->
        <script src="https://kit.fontawesome.com/6d0db64a1f.js" crossorigin="anonymous"></script>
        <title>Devoluciones</title>
    </head>

    <body>
        <jsp:include page="navegador.jsp"></jsp:include>
            <section class="mt-5 opacity">
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-md-9">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="text-center">Listado de devoluciones</h4>
                                </div>
                                <a href="${pageContext.request.contextPath}/download-servlet?ruta=${ruta}"
                               class="btn btn-warning mb-3 w-50 margin-auto">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-download" viewBox="0 0 16 16">
                                <path d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5z"/>
                                <path d="M7.646 11.854a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V1.5a.5.5 0 0 0-1 0v8.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3z"/>
                                </svg> Exportar reporte.
                            </a>
                            <form class="form-inline" method="POST" action="${pageContext.request.contextPath}/controladorReportes?tarea=reporteDevolucionesFechas" >
                                <div class="form-group mx-sm-3 mb-2">
                                    <label for="fecha1Dev" class="form-inline mr-3">Fecha 1:</label>
                                    <input type="date" class="form-control" id="fecha1" name="fecha1Dev">
                                </div>
                                <div class="form-group mx-sm-3 mb-2">
                                    <label for="fecha2Dev" class="form-inline">Fecha 2:</label>
                                    <input type="date" class="form-control mr-3" id="fecha2" name="fecha2Dev">
                                </div>
                                <button type="submit" class="btn btn-primary mb-2">Hacer consulta</button>
                            </form>
                            <table class="table table-striped">

                                <thead class="thead-dark">
                                    <tr class="text-center">
                                        <th>No</th>
                                        <th>Perdida</th>
                                        <th>Fecha</th>
                                        <th>Nit-Cliente</th>
                                        <th>Id-producto</th>
                                        <th>Acción</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="dev" items="${devoluciones}" varStatus="contador">
                                        <tr class="text-center">
                                            <td>${contador.count}</td>
                                            <td><fmt:formatNumber value="${dev.perdida}" type="currency"/></td>
                                            <td> ${dev.fecha}</td>
                                            <td>${dev.nombreCliente}</td>
                                            <td>${dev.idProducto}</td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/controladorReportes?tarea=verDetalleDevolucion&id=${dev.id}"
                                                   class="btn btn-primary">
                                                    <i class="fas fa-arrow-alt-circle-down"></i> Ver detalle
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="card text-center bg-danger text-white mb-3 mt-5">
                            <div class="card-body">
                                <h3>Perdida total</h3>
                                <h4 class="display-4">
                                    <fmt:formatNumber value="${perdidaTotal}" type="currency"/>
                                </h4>
                            </div>
                        </div>        
                    </div>
                </div>
            </div>
        </section>
        <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
        crossorigin="anonymous"></script>

    </body>

</html>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

    if (session.getAttribute("usuario") == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
%>
