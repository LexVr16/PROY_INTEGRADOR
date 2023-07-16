/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.integrador.model.dao.impl;

import com.utp.integrador.model.Transaccion;
import com.utp.integrador.model.dao.TransaccionDao;
import com.utp.integrador.model.dao.repository.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class TransaccionDaoImp extends DataBase implements TransaccionDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public void insert(Transaccion transaccion) {
        try {
            con = this.getConnection();
            ps = con.prepareStatement("INSERT INTO Transaccion(idTransaccion, "
                    + "idUsuario, idCuentaOrigen, idCuentaDestino, idMoneda, "
                    + "montoInicial, montoFinal, fechaTransaccion) VALUES(?,?,?,?,?,?,?,?)");
            
            ps.setString(1, transaccion.getIdTransaccion());
            ps.setString(2, transaccion.getIdUsuario());
            ps.setString(3, transaccion.getIdCuentaOrigen());
            ps.setString(4, transaccion.getIdCuentaDestino());
            ps.setString(5, transaccion.getIdMoneda());
            ps.setDouble(6, transaccion.getMontoInicial());
            ps.setDouble(7, transaccion.getMontoFinal());
            ps.setString(8, transaccion.getFechaTransaccion());
            
            ps.executeUpdate();
            
            ps.close();
            
            System.out.println("SUCCESS TO INSERT TRANSACCION - insert()");
        } catch (SQLException e) {
            System.out.println("ERROR TO INSERT TRANSACCION - insert()");
            System.out.println(e);
        }
    }

    @Override
    public Transaccion find(String v) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Transaccion> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Transaccion t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String v) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
