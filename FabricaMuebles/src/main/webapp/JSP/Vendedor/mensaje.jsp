<%-- 
    Document   : mensaje
    Created on : 20 ago. 2021, 1:46:22
    Author     : luis
--%>
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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <!-- CSS -->
        <link href="../../assets/css/margenes.css" rel="stylesheet" type="text/css"/>
        <title>Solicitando datos del cliente</title>
    </head>
    <body>
        <header id="main-header" class="py-2 bg-info text-white">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="text-center"><i class="fas fa-cog"></i>${mensaje}</h1>
                    </div>
                </div>
            </div>
        </header>
        <div class="container">
            <div class="card w-50 margin-auto pt-1">
                <div class="card-header">
                    <h5>Factura</h5>
                </div>
                <div class="card-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Descripcion</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>No de factura: ${numero}</td>
                            </tr>
                            <tr>
                                <td>Nit: ${cliente.nit}</td>
                            </tr>
                            <tr>
                                <td>Dirección: ${cliente.direccion}</td>
                            </tr>
                            <tr>
                                <td>Mueble: ${mueble.nombre}</td>
                            </tr>
                            <tr>
                                <td>Fecha: ${compra.fecha}</td>
                            </tr>
                            <tr>
                                <td>Costo de la compra: <fmt:formatNumber value="${compra.costo}" type="currency"/></td>
                            </tr>

                        </tbody>
                    </table>

                </div>
                <div class="card-footer text-center text-muted">
                    <a href="../../index.jsp"
                       class="btn btn-danger">
                        <i class="fas fa-angle-double-right"></i> Regresar al menu principal
                    </a>
                </div>
            </div>
        </div>

    </div>
    <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

</body>
</html>
