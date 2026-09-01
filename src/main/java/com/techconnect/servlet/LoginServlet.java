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

/**
 * Servlet del modulo de Login de TechConnect.
 *
 * doGet  -> muestra el formulario HTML de inicio de sesion (login.jsp)
 * doPost -> procesa las credenciales enviadas por el formulario
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("correo");
        String password = request.getParameter("password");

        Usuario usuario = usuarioDAO.autenticar(correo, password);

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioActual", usuario);
            session.setMaxInactiveInterval(30 * 60); // 30 minutos

            response.sendRedirect("perfil");
        } else {
            request.setAttribute("mensajeError", "Correo o contrasena incorrectos. Intenta de nuevo.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
