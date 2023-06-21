package com.utp.integrador.model;

/**
 *
 * @author Usuario
 */
public class Usuario {

    private String idUsuario;
    private String dni;
    private String nombres;
    private String apellidos;
    private String email;
    private String password;

    public Usuario() {
    }

    public Usuario(String idUsuario, String dni, String nombres, String apellidos, String email, String password) {
        this.idUsuario = idUsuario;
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  
}
