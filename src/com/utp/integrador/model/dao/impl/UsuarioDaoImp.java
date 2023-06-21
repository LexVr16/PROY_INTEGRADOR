/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.integrador.model.dao.impl;

import com.utp.integrador.model.Usuario;
import com.utp.integrador.model.dao.UsuarioDao;
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
public class UsuarioDaoImp extends DataBase implements UsuarioDao {

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public void insert(Usuario user) {
        try {
            con = getConnection();
            pst = con.prepareStatement("insert into usuario (idUsuario, dni, "
                    + "nombres, apellidos, email, password) values(?,?,?,?,?,?)");

            pst.setString(1, user.getIdUsuario());
            pst.setString(2, user.getDni());
            pst.setString(3, user.getNombres());
            pst.setString(4, user.getApellidos());
            pst.setString(5, user.getEmail());
            pst.setString(6, user.getPassword());

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
    public Usuario find(String idUsuario) {
        Usuario user = new Usuario();

        try {
            con = getConnection();
            pst = con.prepareStatement("select * from usuario where idUsuario= " + idUsuario + "");

            rs = pst.executeQuery();

            if (rs.next()) {
                user.setDni(rs.getString(2));
                user.setNombres(rs.getString(3));
                user.setApellidos(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPassword(rs.getString(6));

            } else {
                System.out.println("No se encontró Usuario con el idUsuario = " + idUsuario);
            }

            rs.close();
            pst.close();
            con.close();

            System.out.println("SUCCESS TO FIND - find()");
        } catch (SQLException e) {
            System.out.println("ERROR TO FIND - find()");
            System.out.println(e);
        }
        return user;
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> userList = new ArrayList<>();

        try {
            con = getConnection();
            pst = con.prepareStatement("select * from usuario");
            rs = pst.executeQuery();

            while (rs.next()) {
                Usuario user = new Usuario();
                user.setIdUsuario(rs.getString(1));
                user.setDni(rs.getString(2));
                user.setNombres(rs.getString(3));
                user.setApellidos(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPassword(rs.getString(6));

                userList.add(user);
            }

            rs.close();
            pst.close();
            con.close();

            System.out.println("SUCCESS TO FINDALL - findAll()");
        } catch (SQLException e) {
            System.out.println("ERROR TO FINDALL - findAll()");
            System.out.println(e);
        }
        return userList;
    }

    @Override
    public void update(Usuario user) {
        try {
            con = getConnection();
            pst = con.prepareStatement("update usuario set dni=?,"
                    + " nombres=?, apellidos=?, email=?, password=? "
                    + "where idUsuario=?");

            pst.setString(1, user.getDni());
            pst.setString(2, user.getNombres());
            pst.setString(3, user.getApellidos());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getPassword());

            pst.setString(6, user.getIdUsuario());

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
    public void delete(String idUsuario) {
        try {
            con = this.getConnection();
            pst = con.prepareStatement("delete from usuario where idUsuario=?");

            pst.setString(1, idUsuario);

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
