package com.utp.integrador.model;

/**
 *
 * @author Usuario
 */
public class Moneda {

    public static String getId;

    private String id;
    private String nombre;
    private String simbolo;
    private double tipoCambio;
    private String fechaHora;
    private String idPais;

    public Moneda() {
    }

    public Moneda(String id, String nombre, String simbolo, double tipoCambio, String fechaHora, String idPais) {
        this.id = id;
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.tipoCambio = tipoCambio;
        this.fechaHora = fechaHora;
        this.idPais = idPais;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public double getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(double tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    
}
