<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>TechConnect | Iniciar sesion</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body class="pagina-login">

    <div class="tarjeta tarjeta-login">
        <h1 class="logo">Tech<span>Connect</span></h1>
        <p class="subtitulo">Plataforma para tecnicos de telecomunicaciones</p>

        <!-- Muestra el mensaje de error solo si el servlet lo envio -->
        <c:if test="${not empty mensajeError}">
            <div class="alerta alerta-error">${mensajeError}</div>
        </c:if>

        <!-- Formulario HTML que envia los datos por metodo POST al LoginServlet -->
        <form action="login" method="post" class="formulario">
            <div class="campo">
                <label for="correo">Correo electronico</label>
                <input type="email" id="correo" name="correo" placeholder="ejemplo@techconnect.com" required autofocus>
            </div>

            <div class="campo">
                <label for="password">Contrasena</label>
                <input type="password" id="password" name="password" placeholder="Tu contrasena" required>
            </div>

            <button type="submit" class="boton boton-primario">Ingresar</button>
        </form>

        <p class="ayuda">Usuario de prueba: <strong>camilo.rojas@techconnect.com</strong> / clave <strong>12345</strong></p>
    </div>

</body>
</html>
