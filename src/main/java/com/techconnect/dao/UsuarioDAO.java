package com.techconnect.dao;

import com.techconnect.modelo.Usuario;

import java.util.HashMap;
import java.util.Map;

/**
 * Capa de acceso a datos (segun la arquitectura en capas definida para
 * TechConnect: presentacion - logica de negocio - acceso a datos - BD).
 *
 * En esta evidencia se simula la persistencia con una estructura en memoria.
 * En la version final del proyecto esta clase se conecta a la base de datos
 * relacional (MySQL) modelada en las evidencias anteriores, usando JDBC.
 */
public class UsuarioDAO {

    // Simula la tabla "usuario" de la base de datos
    private static final Map<String, Usuario> usuarios = new HashMap<>();

    static {
        // Usuario de prueba precargado para poder iniciar sesion
        Usuario tecnico = new Usuario(
                1,
                "Camilo",
                "Rojas",
                "camilo.rojas@techconnect.com",
                "12345",
                "3001234567",
                "Redes y Fibra Optica",
                "2026-01-15"
        );
        usuarios.put(tecnico.getCorreo(), tecnico);
    }

    /**
     * Busca un usuario por correo y contrasena (autenticacion).
     */
    public Usuario autenticar(String correo, String password) {
        Usuario usuario = usuarios.get(correo);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario;
        }
        return null;
    }

    /**
     * Busca un usuario por su correo (usado para cargar el perfil).
     */
    public Usuario buscarPorCorreo(String correo) {
        return usuarios.get(correo);
    }

    /**
     * Actualiza los datos del perfil de un usuario existente.
     */
    public boolean actualizar(Usuario usuarioActualizado) {
        if (!usuarios.containsKey(usuarioActualizado.getCorreo())) {
            return false;
        }
        usuarios.put(usuarioActualizado.getCorreo(), usuarioActualizado);
        return true;
    }
}
