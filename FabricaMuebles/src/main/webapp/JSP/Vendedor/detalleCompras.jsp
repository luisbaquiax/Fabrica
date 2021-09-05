<%-- 
    Document   : detalleCompras
    Created on : 3 sept. 2021, 2:12:10
    Author     : luis
--%>
<%@page import="entidad.Usuario"%>
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
        <title>Detalle de la factura</title>
    </head>

    <body>
        <%

            Usuario user = (Usuario) request.getSession().getAttribute("usuario");
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        %>

        <jsp:include page="navegador.jsp"></jsp:include>
            <section class="mt-5 opacity">
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="text-center">Detalle de la factura:</h4>
                                </div>
                                <div class="card pt-1">
                                    <div class="card-header">
                                        <h5>Factura: ${venta.id}</h5>
                                    <h5>NIT:  ${cliente.nit} </h5>
                                    <h5>Nombre: ${cliente.nombre}</h5>
                                    <h5>Dirección: ${cliente.direccion}</h5>
                                </div>
                                <div class="card-body">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Producto</th>
                                                <th>Precio unitario</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach var="mueble" items="${mueblesFactura}">
                                                <tr>
                                                    <td>${mueble.nombre}</td>
                                                    <td><fmt:formatNumber value="${mueble.precio}" type="currency"/></td>
                                                </tr>
                                            </c:forEach>
                                            <tr>
                                                <td>Total:</td>
                                                <th></th>
                                                <td><fmt:formatNumber value="${venta.costo}" type="currency"/></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="card-footer text-center text-muted">
                                    <a href="clientesCompras.jsp"
                                       class="btn btn-danger">
                                       <i class="fas fa-angle-double-left"></i> Regresar al menu anterior
                                    </a>
                                </div>
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
