<%-- 
    Document   : LoginRegister
    Created on : 14 ago. 2021, 23:15:09
    Author     : luis
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <!-- estilos CSS -->
        <link href="assets/css/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/margenes.css" rel="stylesheet" type="text/css"/>

        <title>Login</title>
    </head>
    <body>
        <div class="container-md">
            <form class="form-signin" method="POST" action="Login">
                <img class="mb-4 imagen" src="assets/imagenes/login.png" alt="" width="72" height="72">
                <h1 class="h3 mb-3 font-weight-normal">Por favor, regístrese</h1>
                <label for="inputEmail" class="form-signin">Nombre de Usuario:</label>
                <input type="text" id="inputEmail" class="form-control" placeholder="Nombre"  name="nombre" required autofocus>
                <label for="inputPassword" class="form-signin">Contraseña:</label>
                <input type="password" id="inputPassword" class="form-control" placeholder="Contraseña" name="pass" required autofocus>
                <div class="checkbox mb-3">
                    <label>
                        ${no}
                    </label>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                <p class="mt-5 mb-3 text-muted nota">&copy; Muebles S.A. 2021</p>
            </form>
        </div>
        <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

    </body>
</html>
