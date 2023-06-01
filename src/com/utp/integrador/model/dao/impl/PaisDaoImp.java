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
    public Pais find(String id) {
        
        Pais pais = new Pais();

        try {
            con = getConnection();
            pst = con.prepareStatement("select * from pais where id = " + id + "");

            rs = pst.executeQuery();

            if (rs.next()) {
                pais.setId(rs.getString(1));
                pais.setNombre(rs.getString(2));
            } else {
                System.out.println("No se encontró Usuario con el id = " + id);
            }

            rs.close();
            pst.close();
            con.close();

            System.out.println("SUCCESS TO FIND - find()");
        } catch (SQLException e) {
            System.out.println("ERROR TO FIND - find()");
            System.out.println(e);
        }
        return pais;
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
    public void update(Pais pais) {
        try {
            con = getConnection();
            pst = con.prepareStatement("update pais set id=?, descripcion=? where id=?");
            
            pst.setString(1, pais.getNombre());

            pst.setString(2, pais.getId());

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
            pst = con.prepareStatement("delete from articulo where idarticulo=?");

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
