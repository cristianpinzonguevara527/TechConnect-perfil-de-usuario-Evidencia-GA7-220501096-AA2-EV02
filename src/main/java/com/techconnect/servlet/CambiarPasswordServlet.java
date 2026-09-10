package com.techconnect.servlet;

import com.techconnect.dao.UsuarioDAO;
import com.techconnect.modelo.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Servlet del modulo "Cambiar contrasena" de TechConnect (HU-03).
 *
 * doGet  -> muestra el formulario HTML de cambio de contrasena (cambiar_password.jsp)
 * doPost -> valida y procesa el cambio de contrasena
 */
@WebServlet(name = "CambiarPasswordServlet", urlPatterns = {"/cambiar-password"})
public class CambiarPasswordServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    // Minimo 6 y maximo 20 caracteres, con al menos una letra y un numero.
    private static final Pattern PATRON_PASSWORD =
            Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d).{6,20}$");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuarioActual = (session != null) ? (Usuario) session.getAttribute("usuarioActual") : null;

        if (usuarioActual == null) {
            response.sendRedirect("login");
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("cambiar_password.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuarioActual = (session != null) ? (Usuario) session.getAttribute("usuarioActual") : null;

        if (usuarioActual == null) {
            response.sendRedirect("login");
            return;
        }

        String passwordActual = request.getParameter("passwordActual");
        String passwordNueva = request.getParameter("passwordNueva");
        String passwordConfirmar = request.getParameter("passwordConfirmar");

        String mensaje;

        // --- Validaciones de HU-03 (Cambiar contrasena) ---
        if (passwordActual == null || passwordActual.isEmpty()
                || passwordNueva == null || passwordNueva.isEmpty()
                || passwordConfirmar == null || passwordConfirmar.isEmpty()) {
            mensaje = "Todos los campos son obligatorios. No se realizo el cambio.";
        } else if (!PATRON_PASSWORD.matcher(passwordNueva).matches()) {
            mensaje = "La nueva contrasena debe tener entre 6 y 20 caracteres, con al menos una letra y un numero.";
        } else if (!passwordNueva.equals(passwordConfirmar)) {
            mensaje = "La confirmacion no coincide con la nueva contrasena. No se realizo el cambio.";
        } else if (passwordActual.equals(passwordNueva)) {
            mensaje = "La nueva contrasena debe ser diferente a la actual.";
        } else {
            boolean actualizado =  usuarioDAO.cambiarPassword(
         
                    usuarioActual.getCorreo(), passwordActual, passwordNueva);

            mensaje = actualizado
                    ? "Contrasena actualizada correctamente."
                    : "La contrasena actual ingresada es incorrecta. No se realizo el cambio.";
        }

        request.setAttribute("mensaje", mensaje);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cambiar_password.jsp");
        dispatcher.forward(request, response);
    }
}