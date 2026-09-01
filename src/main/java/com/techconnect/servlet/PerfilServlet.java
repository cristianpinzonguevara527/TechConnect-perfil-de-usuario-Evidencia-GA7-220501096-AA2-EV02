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
 * Servlet del modulo "Perfil de usuario" de TechConnect.
 *
 * doGet  -> obtiene el usuario en sesion y lo envia a la vista perfil.jsp
 * doPost -> recibe los datos editados desde el formulario HTML y actualiza el perfil
 */
@WebServlet(name = "PerfilServlet", urlPatterns = {"/perfil"})
public class PerfilServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuarioActual = (session != null) ? (Usuario) session.getAttribute("usuarioActual") : null;

        if (usuarioActual == null) {
            response.sendRedirect("login");
            return;
        }

        // Se recarga el usuario desde el DAO para reflejar cualquier actualizacion reciente
        Usuario usuario = usuarioDAO.buscarPorCorreo(usuarioActual.getCorreo());
        request.setAttribute("usuario", usuario);

        RequestDispatcher dispatcher = request.getRequestDispatcher("perfil.jsp");
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

        // Datos que llegan del formulario de edicion de perfil (metodo POST)
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");
        String especialidad = request.getParameter("especialidad");

        String mensaje;

        if (nombre == null || nombre.trim().isEmpty()
                || apellido == null || apellido.trim().isEmpty()) {
            mensaje = "El nombre y el apellido son obligatorios. No se guardaron los cambios.";
        } else {
            usuarioActual.setNombre(nombre.trim());
            usuarioActual.setApellido(apellido.trim());
            usuarioActual.setTelefono(telefono);
            usuarioActual.setEspecialidad(especialidad);

            boolean actualizado = usuarioDAO.actualizar(usuarioActual);
            session.setAttribute("usuarioActual", usuarioActual);

            mensaje = actualizado
                    ? "Perfil actualizado correctamente."
                    : "No fue posible actualizar el perfil.";
        }

        request.setAttribute("mensaje", mensaje);
        request.setAttribute("usuario", usuarioActual);

        RequestDispatcher dispatcher = request.getRequestDispatcher("perfil.jsp");
        dispatcher.forward(request, response);
    }
}
