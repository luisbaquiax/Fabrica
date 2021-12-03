<%-- 
    Document   : detalleDevolucion
    Created on : 23 nov. 2021, 20:41:23
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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
        <!-- icons -->
        <script src="https://kit.fontawesome.com/6d0db64a1f.js" crossorigin="anonymous"></script>
        <title>Detalle devolución</title>
    </head>
    <body>
        <jsp:include page="navegador.jsp"></jsp:include>
            <section class="mt-5 opacity">
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-footer text-center text-muted">
                                    <a href="devoluciones.jsp"
                                       class="btn btn-danger">
                                        <i class="fas fa-angle-double-left"></i> Regresar al menu anterior
                                    </a>
                                </div>
                                <table class="table table-striped">

                                    <thead class="thead-dark">
                                        <tr class="text-center">
                                            <th>ID</th>
                                            <th>Fecha</th>
                                            <th>Costo</th>
                                            <th>Nit-Cliente</th>
                                            <th>Usuario</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="text-center">
                                            <td>${detallDev.id}</td>
                                        <td> ${detallDev.fecha}</td>
                                        <td><fmt:formatNumber value="${detallDev.costo}" type="currency"/></td>
                                        <td>${detallDev.nitCliente}</td>
                                        <td>${detallDev.usuario}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" 
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" 
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" 
        crossorigin="anonymous"></script>
    </body>
</html>