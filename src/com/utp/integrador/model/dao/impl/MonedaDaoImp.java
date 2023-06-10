/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.integrador.model.dao.impl;

import com.utp.integrador.model.Moneda;
import com.utp.integrador.model.dao.MonedaDao;
import com.utp.integrador.model.dao.repository.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class MonedaDaoImp extends DataBase implements MonedaDao{
private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    
    
    @Override
    public void insert(Moneda t) {
       try {
            con = getConnection();
            pst = con.prepareStatement("insert into Moneda (id,nombre,simbolo,tipoCambio,fechaHora,idPais) values(?,?,?,?,?,?)");

            pst.setString(1,t.getId() );
            pst.setString(2, t.getNombre());
            pst.setString(3, t.getSimbolo());
            pst.setDouble(4, t.getTipoCambio());
            pst.setString(5, t.getFechaHora());
            pst.setString(6, t.getIdPais());

            pst.executeUpdate();

            pst.close();
            con.close();

            System.out.println("SUCCESS TO INSERT - insert()");
        } catch (SQLException e) {
            System.out.println("ERROR TO INSERT - insert()");
            System.out.println(e);
        }
    }  
        
    

    @Override
    public Moneda find(String id) {
        Moneda moneda=new Moneda();
        try {
            con = getConnection();
            pst = con.prepareStatement("select * from Moneda where id = " + id + "");

            rs = pst.executeQuery();

            if (rs.next()) {
                moneda.setId(rs.getString(1));
                moneda.setNombre(rs.getString(2));
                moneda.setSimbolo(rs.getString(3));
                moneda.setTipoCambio(rs.getDouble(4));
                moneda.setFechaHora(rs.getString(5));
                moneda.setIdPais(rs.getString(6));
            } else {
                System.out.println("No se encontró el Tippo de Moneda con el id = " + id);
            }

            rs.close();
            pst.close();
            con.close();

            System.out.println("SUCCESS TO FIND - find()");
        } catch (SQLException e) {
            System.out.println("ERROR TO FIND - find()");
            System.out.println(e);
        }
        return moneda;
        
    }

    @Override
    public List<Moneda> findAll() {
        List<Moneda> Monedas = new ArrayList<>(); 
        try {
            con = getConnection();
            pst = con.prepareStatement("select * from Moneda");
            rs = pst.executeQuery();

            while (rs.next()) {
                Moneda moneda = new Moneda();

                moneda.setId(rs.getString(1));
                moneda.setNombre(rs.getString(2));
                moneda.setSimbolo(rs.getString(3));
                moneda.setTipoCambio(rs.getDouble(4));
                moneda.setFechaHora(rs.getString(5));
                moneda.setIdPais(rs.getString(6));

                Monedas.add(moneda);
            }

            rs.close();
            pst.close();
            con.close();

            System.out.println("SUCCESS TO FINDALL - findAll()");
        } catch (SQLException e) {
            System.out.println("ERROR TO FINDALL - findAll()");
            System.out.println(e);
        }
  
    return Monedas;
    
    }

    @Override
    public void update(Moneda moneda) {
   try {
            con = getConnection();
            pst = con.prepareStatement("update Moneda set nombre=?, simbolo=?, tipoCambio=?, fechaHora=?, idPais=? where id=?");
            
            pst.setString(1, moneda.getNombre());
            pst.setString(2, moneda.getSimbolo());
            pst.setDouble(3, moneda.getTipoCambio());
            pst.setString(4, moneda.getFechaHora());
            pst.setString(5, moneda.getIdPais());

            pst.setString(6, moneda.getId());

            pst.executeUpdate();

            pst.close();
            con.close();

            System.out.println("SUCCESS TO UPDATE - update()");
        } catch (SQLException e) {
            System.out.println("ERROR TO UPDATE - update()");
            System.out.println(e);
        }    
    }

    @Override
    public void delete(String id) {
 try {
            con = this.getConnection();
            pst = con.prepareStatement("delete from Moneda where id=?");

            pst.setString(1, id);

            pst.executeUpdate();

            pst.close();
            con.close();

            System.out.println("SUCCESS TO DELETE - delete()");
        } catch (SQLException e) {
            System.out.println("ERROR TO DELETE - delete()");
            System.out.println(e);
        }
    }    
}
    

