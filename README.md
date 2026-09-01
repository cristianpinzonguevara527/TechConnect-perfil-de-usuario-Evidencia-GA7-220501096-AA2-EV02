# TechConnect - Modulo Perfil de Usuario (Servlets + JSP)

Evidencia AA2_EV02 - Codificacion del modulo del proyecto TechConnect, enfocado a
desarrollo web con Servlets, formularios HTML y JSP, segun lo definido en los
artefactos previos del ciclo de vida del software (diagrama de clases, historias
de usuario, prototipos y diseno de base de datos).

## Modulo implementado

Login + Perfil de usuario del tecnico de telecomunicaciones:

- **LoginServlet** (`/login`): `doGet` muestra el formulario HTML de inicio de
  sesion (`login.jsp`); `doPost` valida las credenciales enviadas.
- **PerfilServlet** (`/perfil`): `doGet` consulta y muestra los datos del
  usuario autenticado (`perfil.jsp`); `doPost` procesa el formulario de edicion
  y actualiza los datos.
- **LogoutServlet** (`/logout`): cierra la sesion del usuario.

## Tecnologias

- Java 11
- Servlets 4.0 / JSP 2.3 (paquete `javax.*`, compatible con Apache Tomcat 8.5/9)
- JSTL 1.2 (`c:if`, `c:out`) para logica de presentacion en las vistas
- Maven (empaquetado `.war`)

## Estructura del proyecto

```
techconnect-perfil-servlets/
├── pom.xml
├── src/main/java/com/techconnect/
│   ├── modelo/Usuario.java
│   ├── dao/UsuarioDAO.java          (capa de datos simulada, lista para JDBC)
│   └── servlet/
│       ├── LoginServlet.java
│       ├── PerfilServlet.java
│       └── LogoutServlet.java
└── src/main/webapp/
    ├── index.jsp
    ├── login.jsp
    ├── perfil.jsp
    ├── css/estilos.css
    └── WEB-INF/web.xml
```

## Como ejecutarlo

1. Importar el proyecto como **Maven Project** en Eclipse, IntelliJ o NetBeans.
2. Configurar un servidor **Apache Tomcat 8.5 o 9**.
3. Ejecutar `mvn clean package` para generar `techconnect-perfil.war`, o
   correr el proyecto directamente sobre el servidor desde el IDE.
4. Abrir en el navegador: `http://localhost:8080/techconnect-perfil/`
5. Usuario de prueba:
   - Correo: `camilo.rojas@techconnect.com`
   - Contrasena: `12345`

> Nota: el `UsuarioDAO` simula la persistencia en memoria para efectos de la
> evidencia. En la version final del proyecto se reemplaza por acceso JDBC a
> la base de datos MySQL ya modelada en las evidencias anteriores.

## Control de versiones (Git)

Pasos sugeridos para versionar y subir el proyecto a un repositorio remoto
(GitHub / GitLab):

```bash
git init
git add .
git commit -m "Modulo login y perfil de usuario con servlets y JSP - AA2_EV02"
git branch -M main
git remote add origin https://github.com/TU-USUARIO/techconnect-perfil-servlets.git
git push -u origin main
```

Luego de subirlo, copia el enlace del repositorio en el archivo
`enlace_repositorio.txt` (incluido en la entrega) antes de comprimir la
carpeta final.
