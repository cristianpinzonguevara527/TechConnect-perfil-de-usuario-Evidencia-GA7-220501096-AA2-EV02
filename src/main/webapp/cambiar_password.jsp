<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>TechConnect | Cambiar contrasena</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>

    <header class="barra-superior">
        <h1 class="logo">Tech<span>Connect</span></h1>
        <nav>
            <a href="perfil" class="enlace-salir">Volver al perfil</a>
            <a href="logout" class="enlace-salir">Cerrar sesion</a>
        </nav>
    </header>

    <main class="contenedor">
        <div class="tarjeta">
            <h2>Cambiar contrasena</h2>
            <p class="descripcion">Por seguridad, ingresa tu contrasena actual antes de definir una nueva (HU-03).</p>

            <!-- Mensaje de resultado enviado por el servlet tras el POST -->
            <c:if test="${not empty mensaje}">
                <div class="alerta ${mensaje.contains('correctamente') ? 'alerta-exito' : 'alerta-error'}">
                        ${mensaje}
                </div>
            </c:if>

            <!-- Formulario HTML que envia los datos por metodo POST al CambiarPasswordServlet -->
            <form action="cambiar-password" method="post" class="formulario">

                <div class="campo">
                    <label for="passwordActual">Contrasena actual</label>
                    <input type="password" id="passwordActual" name="passwordActual"
                           placeholder="Tu contrasena actual" required maxlength="20">
                </div>

                <div class="campo">
                    <label for="passwordNueva">Nueva contrasena</label>
                    <input type="password" id="passwordNueva" name="passwordNueva"
                           placeholder="Minimo 6 caracteres, letras y numeros" required
                           minlength="6" maxlength="20">
                    <small>Debe tener entre 6 y 20 caracteres, con al menos una letra y un numero.</small>
                </div>

                <div class="campo">
                    <label for="passwordConfirmar">Confirmar nueva contrasena</label>
                    <input type="password" id="passwordConfirmar" name="passwordConfirmar"
                           placeholder="Repite la nueva contrasena" required
                           minlength="6" maxlength="20">
                </div>

                <button type="submit" class="boton boton-primario">Actualizar contrasena</button>
            </form>
        </div>
    </main>

</body>
</html>
