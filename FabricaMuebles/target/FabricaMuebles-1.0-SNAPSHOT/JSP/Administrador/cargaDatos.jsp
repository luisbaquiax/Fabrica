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
                                <a class="nav-link" href="#medicos">Muebles<span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#lab">Piezas</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#pacientes">Pacientes</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#examen">Examenes</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#reporte">Reportes</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#resultado">Resultados</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#cita">Citas</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#consulta">Consultas</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="">Errores</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
        </div>
        <div class="container-fluider btn-warning">
            <div class="py-5 text-center">
                <img class="d-block mx-auto mb-1 mt-5" src="../../assets/imagenes/taller.jpg" alt="" width="72" height="72">
                <h2>Carga de datos.</h2>
                <p class="lead">Por favor seleccione el archivo para subir los datos</p>
            </div>
            <div class="mb-3">
                <form class="form-inline" method="POST" action="../../cargaDatos" enctype="multipart/form-data">
                    <div class="form-group mb-2 mr-5">
                        <input type="file" class="form-control-file ml-5" id="staticEmail2" name="archivo">
                    </div>
                    <button type="submit" class="btn btn-primary mb-2 margin-auto" >Ver datos a subir</button>
                    <a href="${pageContext.request.contextPath}/FabricaControlador?tarea=descendente"
                       class="btn btn-success mb-1 margin-auto">
                        <i class="fas fa-angle-double-down"></i> SUBIR DATOS
                    </a>
                </form>

            </div>
        </div>


        <!--medicos-->
        <section id="medicos" class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="">Listado de Medicos</h4>
                            </div>
                            <table class="table table-striped thead-dark">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>No</th>
                                        <th>Codigo</th>
                                        <th>Nombre</th>
                                        <th>Numero de colegiado</th>
                                        <th>DPI</th>
                                        <th>Telefono</th>
                                        <th>Correo</th>
                                        <th>Horario de inicio</th>
                                        <th>Horario fin</th>
                                        <th>Fecha de inicio de trabajo</th>
                                        <th>Contraseña</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- Iteramos cada elemento de la lista de medicos -->
                                    <c:forEach var="medico" items="${medicos}" varStatus="status" >
                                        <tr>
                                            <td>${status.count}</td>
                                            <td>${medico.codigo}</td> 
                                            <td>${medico.nombre}</td>
                                            <td>${medico.colegiado}</td>
                                            <td>${medico.dpi}</td> 
                                            <td>${medico.telefono}</td>
                                            <td>${medico.email}</td>
                                            <td>${medico.horarioInicio}</td> 
                                            <td>${medico.horarioFin}</td>
                                            <td>${medico.fechaInicioTrabajo}</td>
                                            <td>${medico.contraseña}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--laboratoristas-->
        <section id="lab" class="section">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="">Listado de Laboratoristas</h4>
                            </div>
                            <table class="table table-striped thead-dark">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>No</th>
                                        <th>Codigo</th>
                                        <th>Nombre</th>
                                        <th>Registro</th>
                                        <th>DPI</th>
                                        <th>Telefono</th>
                                        <th>Tipo de Examen</th>
                                        <th>Email</th>
                                        <th>Fecha de inicio de trabajo</th>
                                        <th>Contraseña</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- Iteramos cada elemento de la lista de laboratoristas -->
                                    <c:forEach var="lab" items="${labs}" varStatus="status" >
                                        <tr>
                                            <td>${status.count}</td>
                                            <td>${lab.codigo}</td> 
                                            <td>${lab.nombre}</td>
                                            <td>${lab.registro}</td>
                                            <td>${lab.dpi}</td>
                                            <td>${lab.telefono}</td>
                                            <td>${lab.tipoExamen}</td>
                                            <td>${lab.email}</td>
                                            <td>${lab.fechaInicioTrabajo}</td>
                                            <td>${lab.contraseña}</td>
                                        </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--Pacientes-->
        <section id="pacientes" class="section">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="">Listado de Pacientes</h4>
                            </div>
                            <table class="table table-striped thead-dark">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>No</th>
                                        <th>Codigo</th>
                                        <th>Nombre</th>
                                        <th>Sexo</th>
                                        <th>Fecha de nacimiento</th>
                                        <th>DPI</th>
                                        <th>Telefono</th>
                                        <th>Peso</th>
                                        <th>Tipo Sangre</th>
                                        <th>Email</th>
                                        <th>Contraseña</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- Iteramos cada elemento de la lista de pacientes -->
                                    <c:forEach var="paciente" items="${pacientes}" varStatus="status" >
                                        <tr>
                                            <td>${status.count}</td> 
                                            <td>${paciente.codigo}</td>
                                            <td>${paciente.nombre}</td>
                                            <td>${paciente.sexo}</td>
                                            <td>${paciente.fechaNacimiento}</td>
                                            <td>${paciente.dpi}</td>
                                            <td>${paciente.telefono}</td>
                                            <td>${paciente.peso}</td>
                                            <td>${paciente.tipoSangre}</td>
                                            <td>${paciente.email}</td>
                                            <td>${paciente.contraseña}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!--Examenes de laboratorio-->
        <section id="examen" class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="">Examenes de laboratorio</h4>
                            </div>
                            <table class="table table-striped thead-dark">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>No</th>
                                        <th>Codigo</th>
                                        <th>Nombre</th>
                                        <th>Orden</th>
                                        <th>Descripción</th>
                                        <th>Costo</th>
                                        <th>Informe</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- Iteramos cada elemento de la lista de examenes -->
                                    <c:forEach var="examen" items="${examenes}" varStatus="status" >
                                        <tr>
                                            <td>${status.count}</td> 
                                            <td>${examen.codigo}</td>
                                            <td>${examen.nombre}</td>
                                            <td>${examen.orden}</td>
                                            <td>${examen.descripcion}</td>
                                            <td><fmt:formatNumber value="${examen.costo}" type="currency"/></td>
                                            <td>${examen.informe}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!--Reportes-->
        <section id="reporte" class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="">Informes</h4>
                            </div>
                            <table class="table table-striped thead-dark">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>No</th>
                                        <th>Codigo</th>
                                        <th>Paciente</th>
                                        <th>Medico</th>
                                        <th>Informe</th>
                                        <th>Fecha</th>
                                        <th>Hora</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- Iteramos cada elemento de la lista de examenes -->
                                    <c:forEach var="reporte" items="${reportes}" varStatus="status" >
                                        <tr>
                                            <td>${status.count}</td> 
                                            <td>${reporte.codigo}</td>
                                            <td>${reporte.codigoPaciente}</td>
                                            <td>${reporte.codigoMedico}</td>
                                            <td>${reporte.informe}</td>
                                            <td>${reporte.fecha}</td>
                                            <td>${reporte.hora}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!--Resultados-->
        <section id="resultado" class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="">Resultados</h4>
                            </div>
                            <table class="table table-striped thead-dark">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>No</th>
                                        <th>Codigo</th>
                                        <th>Paciente</th>
                                        <th>Medico</th>
                                        <th>Examen</th>
                                        <th>Laboratorista</th>
                                        <th>Orden</th>
                                        <th>Informe</th>
                                        <th>Fecha</th>
                                        <th>Hora</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- Iteramos cada elemento de la lista de resultados -->
                                    <c:forEach var="resultado" items="${resultados}" varStatus="status" >
                                        <tr>
                                            <td>${status.count}</td> 
                                            <td>${resultado.codigo}</td>
                                            <td>${resultado.codigoPaciente}</td>
                                            <td>${resultado.codigoMedico}</td>
                                            <td>${resultado.codigoExamen}</td>
                                            <td>${resultado.codigoLaboratorista}</td>
                                            <td>${resultado.ordenPath}</td>
                                            <td>${resultado.informePath}</td>
                                            <td>${resultado.fecha}</td>
                                            <td>${resultado.hora}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!--Citas-->
        <section id="cita" class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="">Citas</h4>
                            </div>
                            <table class="table table-striped thead-dark">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>No</th>
                                        <th>Codigo</th>
                                        <th>Paciente</th>
                                        <th>Medico</th>
                                        <th>Fecha</th>
                                        <th>Hora</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- Iteramos cada elemento de la lista de resultados -->
                                    <c:forEach var="cita" items="${citas}" varStatus="status" >
                                        <tr>
                                            <td>${status.count}</td> 
                                            <td>${cita.codigo}</td>
                                            <td>${cita.codigoPaciente}</td>
                                            <td>${cita.codigoMedico}</td>
                                            <td>${cita.fecha}</td>
                                            <td>${cita.hora}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!--Consulta-->
        <section id="consulta" class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="">Consultas</h4>
                            </div>
                            <table class="table table-striped thead-dark">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>No</th>
                                        <th>Tipo</th>
                                        <th>Costo</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- Iteramos cada elemento de la lista de resultados -->
                                    <c:forEach var="consulta" items="${consultas}" varStatus="status" >
                                        <tr>
                                            <td>${status.count}</td> 
                                            <td>${consulta.tipoConsulta}</td>
                                            <td>${consulta.costo}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

    </body>
</html>