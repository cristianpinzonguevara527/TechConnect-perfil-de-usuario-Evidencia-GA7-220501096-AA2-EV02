<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>TechConnect | Mi perfil</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>

    <header class="barra-superior">
        <h1 class="logo">Tech<span>Connect</span></h1>
        <nav>
            <span>Hola, <c:out value="${usuario.nombre}"/></span>
            <a href="logout" class="enlace-salir">Cerrar sesion</a>
        </nav>
    </header>

    <main class="contenedor">
        <div class="tarjeta">
            <h2>Mi perfil</h2>
            <p class="descripcion">Consulta y actualiza tus datos como tecnico registrado en TechConnect.</p>

            <!-- Mensaje de resultado enviado por el servlet tras el POST -->
            <c:if test="${not empty mensaje}">
                <div class="alerta ${mensaje.contains('correctamente') ? 'alerta-exito' : 'alerta-error'}">
                        ${mensaje}
                </div>
            </c:if>

            <!-- Formulario HTML precargado con los datos actuales (obtenidos por GET) -->
            <!-- Al enviarse, se procesa por metodo POST en el mismo PerfilServlet   -->
            <form action="perfil" method="post" class="formulario">

                <div class="fila">
                    <div class="campo">
                        <label for="nombre">Nombre</label>
                        <input type="text" id="nombre" name="nombre" value="${usuario.nombre}" required>
                    </div>

                    <div class="campo">
                        <label for="apellido">Apellido</label>
                        <input type="text" id="apellido" name="apellido" value="${usuario.apellido}" required>
                    </div>
                </div>

                <div class="campo">
                    <label for="correo">Correo electronico</label>
                    <input type="email" id="correo" name="correo" value="${usuario.correo}" disabled>
                    <small>El correo no se puede modificar desde este formulario.</small>
                </div>

                <div class="fila">
                    <div class="campo">
                        <label for="telefono">Telefono</label>
                        <input type="tel" id="telefono" name="telefono" value="${usuario.telefono}" placeholder="3001234567">
                    </div>

                    <div class="campo">
                        <label for="especialidad">Especialidad</label>
                        <select id="especialidad" name="especialidad">
                            <option value="Redes y Fibra Optica" ${usuario.especialidad == 'Redes y Fibra Optica' ? 'selected' : ''}>Redes y Fibra Optica</option>
                            <option value="Telefonia IP" ${usuario.especialidad == 'Telefonia IP' ? 'selected' : ''}>Telefonia IP</option>
                            <option value="Mantenimiento de equipos" ${usuario.especialidad == 'Mantenimiento de equipos' ? 'selected' : ''}>Mantenimiento de equipos</option>
                            <option value="Soporte a usuario final" ${usuario.especialidad == 'Soporte a usuario final' ? 'selected' : ''}>Soporte a usuario final</option>
                        </select>
                    </div>
                </div>

                <div class="campo">
                    <label>Fecha de registro</label>
                    <input type="text" value="${usuario.fechaRegistro}" disabled>
                </div>

                <button type="submit" class="boton boton-primario">Guardar cambios</button>
            </form>
        </div>
    </main>

</body>
</html>
