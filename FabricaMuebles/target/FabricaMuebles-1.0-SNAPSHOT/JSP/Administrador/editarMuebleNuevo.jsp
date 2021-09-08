<%-- 
    Document   : editarMuebleNuevo
    Created on : 6 sept. 2021, 0:27:12
    Author     : luis
--%>


<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <!-- Bootstrap CSS -->
        <link href="../../assets/css/general.css" rel="stylesheet" type="text/css"/>
        <link href="../../assets/css/margenes.css" rel="stylesheet" type="text/css"/>
        <!-- icons -->
        <script src="https://kit.fontawesome.com/6d0db64a1f.js" crossorigin="anonymous"></script>
        <title>Crear Mueble</title>
    </head>
    <body>
        <jsp:include page="navegador.jsp"></jsp:include>
            <div class="container">
                <div class="card pt-5">
                    <div class="card-header pt-2 bg-warning">
                        <h1 class="text-center h1">Establecer cantidad de piezas para el nuevo mueble</h1>
                    </div>
                    <a href="Financiero.jsp"
                       class="btn btn-danger margin-auto mt-3 w-50">
                        <i class="fas fa-window-close"></i> Cancelar
                    </a>
                    <a href="${pageContext.request.contextPath}/controladorAdmin?tarea=guardar"
                   class="btn btn-primary margin-auto mt-3 w-50">
                    <i class="fas fa-angle-double-down"></i> Guardar mueble
                </a>
                <div class="card-body">

                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr class="text-center">
                                <th>Tipo pieza</th>
                                <th>Cantidad actual</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="pieza" items="${piezasNecesarias}">
                                <tr class="text-center">
                                    <td>${pieza.tipo}</td>
                                    <td>${pieza.cantidadExistente}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/controladorAdmin?tarea=editarCantidad&pieza=${pieza.tipo}"
                                           class="btn btn-primary">
                                            <i class="fa-solid fa-pen-field"></i> Agregar cantidad
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

    </body>
</html>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

    if (session.getAttribute("usuario") == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
%>