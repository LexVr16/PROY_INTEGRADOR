package com.utp.integrador.model;

/**
 *
 * @author Usuario
 */
public class CuentaBancaria {

    private String id;
    private String dni;
    private String nombre;
    private String tipoCuenta;
    private String nroDeCuenta;

    public CuentaBancaria() {
    }

    public CuentaBancaria(String id, String dni, String nombre, String tipoCuenta, String nroDeCuenta) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.tipoCuenta = tipoCuenta;
        this.nroDeCuenta = nroDeCuenta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getNroDeCuenta() {
        return nroDeCuenta;
    }

    public void setNroDeCuenta(String nroDeCuenta) {
        this.nroDeCuenta = nroDeCuenta;
    }

   

}
