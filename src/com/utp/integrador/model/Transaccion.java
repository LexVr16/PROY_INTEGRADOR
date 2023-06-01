package com.utp.integrador.model;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Transaccion {
    private int id;
    private int idUsuario;
    private int idMonedaOrigen;
    private int idMonedaDestino;
    private float montoOrigen;
    private float montoDestino;
    private float tipoCambio;
    private Date fecha;
}
