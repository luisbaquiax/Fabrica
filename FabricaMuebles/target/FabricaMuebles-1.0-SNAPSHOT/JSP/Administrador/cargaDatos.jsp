<%-- 
    Document   : cargaDatos
    Created on : 27 ago. 2021, 12:13:38
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
        <!-- personalizados CSS -->
        <link href="../../assets/css/margenes.css" rel="stylesheet" type="text/css"/>
        <title>Carga de datos</title>
    </head>
    <body>

        <div class="box-menu">
            <header class="menu">
                <nav class="navbar navbar-expand navbar-dark bg-dark rounded fixed-top">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample10" aria-controls="navbarsExample10" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample10">
                        <ul class="navbar-nav">
                            <li class="nav-item active">
                                <a class="nav-link" href="#datos">Inicio<span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="#usuarios">Usuarios<span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#muebles">Muebles</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#piezas">Piezas y precios</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#requerimientos">Requerimientos ENSAMBLE_PIEZA</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#ensamblajes">Ensamblajes</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#clientes">Clientes</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
        </div>
        <section class="section" id="datos">
            <div class="container-fluider btn-warning">
                <div class="py-5 text-center">
                    <img class="d-block mx-auto mb-1 mt-5" src="../../assets/imagenes/taller.jpg" alt="" width="172" height="172">
                    <h2>Carga de datos.</h2>
                    <p class="lead">Por favor seleccione el archivo para subir los datos</p>
                </div>
                <div class="mb-3">
                    <form class="form-inline" method="POST" action="../../cargaDatos" enctype="multipart/form-data">
                        <div class="form-group mb-2 mr-5 margin-auto">
                            <input type="file" class="form-control-file ml-5" name="archivo">
                        </div>
                        <button type="submit" class="btn btn-primary mb-2 margin-auto" >Ver datos a subir</button>
                    </form>
                    <div class="form-group">
                        <a href="${pageContext.request.contextPath}/cargaDatos?tarea=subir"
                           class="btn btn-success mb-1">
                            <i class="fas fa-angle-double-down"></i> SUBIR DATOS
                        </a>
                    </div>
                </div>
            </div>
        </section>

        <c:if test="${carga!=null}">
            <!--usuarios-->
            <section id="usuarios" class="section">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="text-center">Usuarios a ingresar</h4>
                                </div>
                                <table class="table table-striped thead-dark">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>No</th>
                                            <th>Nombre</th>
                                            <th>Pass</th>
                                            <th>Tipo</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- Iteramos cada elemento de la lista de usuarios -->
                                        <c:forEach var="usuario" items="${carga.usuarios}" varStatus="status" >
                                            <tr>
                                                <td>${status.count}</td>
                                                <td>${usuario.nombre}</td> 
                                                <td>${usuario.password}</td>
                                                <td>${usuario.tipo}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <!--muebles-->
            <section id="muebles" class="section mt-5">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="text-center">Muebles a ingresar</h4>
                                </div>
                                <table class="table table-striped thead-dark">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>No</th>
                                            <th>Nombre</th>
                                            <th>Precio de venta</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- Iteramos cada elemento de la lista de muebles -->
                                        <c:forEach var="mueble" items="${carga.muebles}" varStatus="status" >
                                            <tr>
                                                <td>${status.count}</td>
                                                <td>${mueble.nombre}</td> 
                                                <td>${mueble.precio}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <!--piezas y precios-->
            <section id="piezas" class="section mt-5">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="text-center">Piezas y precios</h4>
                                </div>
                                <table class="table table-striped thead-dark">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>No</th>
                                            <th>Tipo</th>
                                            <th>Costo</th>
                                            <th>Cantidad de existencia</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- Iteramos cada elemento de la lista de muebles -->
                                        <c:forEach var="pieza" items="${carga.piezasTipoCosto}" varStatus="status" >
                                            <tr>
                                                <td>${status.count}</td>
                                                <td>${pieza.tipo}</td> 
                                                <td>${pieza.costo}</td>
                                                <td>${pieza.cantidadExistente}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <!--ensamble piezas-->
            <section id="requerimientos" class="section mt-5">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="text-center">Requerimientos</h4>
                                </div>
                                <table class="table table-striped thead-dark">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>No</th>
                                            <th>Mueble</th>
                                            <th>Pieza requerida</th>
                                            <th>Cantidad</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- Iteramos cada elemento de la lista de muebles -->
                                        <c:forEach var="reque" items="${carga.auxRequerimientosAsubir}" varStatus="status" >
                                            <tr>
                                                <td>${status.count}</td>
                                                <td>${reque.mueble}</td>
                                                <td>${reque.pieza}</td>
                                                <td>${reque.cantidadPiezas}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <!--ensamblajes-->
            <section id="ensamblajes" class="section mt-5">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="text-center">Ensamblajes</h4>
                                </div>
                                <table class="table table-striped thead-dark">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>No</th>
                                            <th>Usuario</th>
                                            <th>Mueble</th>
                                            <th>Costo de ensamblaje</th>
                                            <th>Fecha de ensamblaje</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- Iteramos cada elemento de la lista de ensamblajes -->
                                        <c:forEach var="ensamblaje" items="${carga.auxEnsamblajes}" varStatus="status" >
                                            <tr>
                                                <td>${status.count}</td>
                                                <td>${ensamblaje.usuario}</td> 
                                                <td>${ensamblaje.mueble}</td>
                                                <td>${ensamblaje.costo}</td>
                                                <td>${ensamblaje.fecha}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <!--clientes-->
            <section id="clientes" class="section mt-5">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="text-center">Clientes</h4>
                                </div>
                                <table class="table table-striped thead-dark">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>No</th>
                                            <th>NIT</th>
                                            <th>Nombre</th>
                                            <th>Dirección</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- Iteramos cada elemento de la lista de clientes -->
                                        <c:forEach var="cliente" items="${carga.clientes}" varStatus="status" >
                                            <tr>
                                                <td>${status.count}</td>
                                                <td>${cliente.nit}</td> 
                                                <td>${cliente.nombre}</td>
                                                <td>${cliente.direccion}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

        </c:if>

        <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

    </body>
</html>