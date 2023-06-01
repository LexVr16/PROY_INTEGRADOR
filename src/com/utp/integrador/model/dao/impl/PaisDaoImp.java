/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.integrador.model.dao.impl;

import com.utp.integrador.model.Pais;
import com.utp.integrador.model.dao.PaisDao;
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
public class PaisDaoImp extends DataBase implements PaisDao {

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public void insert(Pais pais) {
        try {
            con = getConnection();
            pst = con.prepareStatement("insert into pais (id, "
                    + "nombre) values(?,?)");

            pst.setString(1, pais.getId());
            pst.setString(2, pais.getNombre());

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
    public Pais find(String v) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Pais> findAll() {
        List<Pais> Paises = new ArrayList<>();

        try {
            con = getConnection();
            pst = con.prepareStatement("select * from pais");
            rs = pst.executeQuery();

            while (rs.next()) {
                Pais pais = new Pais();

                pais.setId(rs.getString(1));
                pais.setNombre(rs.getString(1));

                Paises.add(pais);
            }

            rs.close();
            pst.close();
            con.close();

            System.out.println("SUCCESS TO FINDALL - findAll()");
        } catch (SQLException e) {
            System.out.println("ERROR TO FINDALL - findAll()");
            System.out.println(e);
        }

        return Paises;
    }

    @Override
    public void update(Pais t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String v) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
