package com.utp.integrador.model;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Transaccion {
    private String idTransaccion;
    private String idUsuario;
    private String idCuentaOrigen;
    private String idCuentaDestino;
    private String idMoneda;
    private double montoInicial;
    private double montoFinal;
    private String fechaTransaccion;

    public Transaccion() {
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdCuentaOrigen() {
        return idCuentaOrigen;
    }

    public void setIdCuentaOrigen(String idCuentaOrigen) {
        this.idCuentaOrigen = idCuentaOrigen;
    }

    public String getIdCuentaDestino() {
        return idCuentaDestino;
    }

    public void setIdCuentaDestino(String idCuentaDestino) {
        this.idCuentaDestino = idCuentaDestino;
    }

    public String getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(String idMoneda) {
        this.idMoneda = idMoneda;
    }

    public double getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(double montoInicial) {
        this.montoInicial = montoInicial;
    }

    public double getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(double montoFinal) {
        this.montoFinal = montoFinal;
    }

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }
    
}
