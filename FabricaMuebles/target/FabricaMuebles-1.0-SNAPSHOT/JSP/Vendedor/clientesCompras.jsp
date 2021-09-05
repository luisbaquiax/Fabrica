<%-- 
    Document   : clientesCompras
    Created on : 3 sept. 2021, 1:09:37
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
        <!-- icons -->
        <script src="https://kit.fontawesome.com/6d0db64a1f.js" crossorigin="anonymous"></script>
        <title>Compras y clientes</title>
    </head>

    <body>
        <jsp:include page="navegador.jsp"></jsp:include>
            <section class="mt-5 opacity">
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="text-center">Compras del cliente con NIT: ${nit}</h4>
                            </div>

                            <c:if test="${compras.size()==0}">
                                <h1 class="text-center">Este cliente no ha hecho una compra</h1>
                            </c:if>
                            <a href="${pageContext.request.contextPath}/controladorTienda?tarea=listadoClientes"
                               class="btn btn-warning mb-3">
                                <i class="fas fa-angle-double-left"></i> Regresar
                            </a>
                            <c:if test="${compras.size()>0}">
                                <form class="form-inline" method="POST" action="${pageContext.request.contextPath}/controladorTienda?tarea=verComprasFecha&nit=${nit}" >
                                    <div class="form-group mx-sm-3 mb-2">
                                        <label for="fecha1" class="form-inline mr-3">Fecha 1:</label>
                                        <input type="date" class="form-control" id="fecha1" name="fecha1">
                                    </div>
                                    <div class="form-group mx-sm-3 mb-2">
                                        <label for="fecha2" class="form-inline">Fecha 2:</label>
                                        <input type="date" class="form-control mr-3" id="fecha2" name="fecha2">
                                    </div>
                                    <button type="submit" class="btn btn-primary mb-2">Hacer consulta</button>
                                </form>
                                <table class="table table-striped">

                                    <thead class="thead-dark">
                                        <tr class="text-center">
                                            <th>No</th>
                                            <th>Identificador de la compra</th>
                                            <th>Fecha</th>
                                            <th>Costo</th>
                                            <th>NIT</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- se itera el listado de compras de un cliente--> 
                                        <c:forEach var="compra" items="${compras}" varStatus="contador">
                                            <tr class="text-center">
                                                <td>${contador.count}</td> <!-- puede ser esto, o el ID del cliente--> 
                                                <td>${compra.id}</td>
                                                <td> ${compra.fecha}</td>
                                                <td><fmt:formatNumber value="${compra.costo}" type="currency"/></td>
                                                <td>${compra.nitCliente}</td>
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/controladorTienda?tarea=verDetalle&id=${compra.id}"
                                                       class="btn btn-primary">
                                                        <i class="fas fa-arrow-alt-circle-down"></i> Ver detalle de compra
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>

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