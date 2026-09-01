package com.techconnect.modelo;

import java.io.Serializable;

/**
 * Modelo que representa a un tecnico registrado en TechConnect.
 * Corresponde al artefacto "Diagrama de clases" definido en fases anteriores
 * del ciclo de vida del proyecto.
 */
public class Usuario implements Serializable {

    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private String telefono;
    private String especialidad; // Ej: Redes, Fibra optica, Telefonia, etc.
    private String fechaRegistro;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellido, String correo, String password,
                   String telefono, String especialidad, String fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
        this.telefono = telefono;
        this.especialidad = especialidad;
        this.fechaRegistro = fechaRegistro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}
