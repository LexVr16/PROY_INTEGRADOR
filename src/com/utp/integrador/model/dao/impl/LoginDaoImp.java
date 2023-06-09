/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.integrador.model.dao.impl;

import com.utp.integrador.model.Usuario;
import com.utp.integrador.model.dao.LoginDao;
import com.utp.integrador.model.dao.repository.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class LoginDaoImp extends DataBase implements LoginDao {

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public Usuario validateLogin(String dni, String password) {
        Usuario usuario = null;
        try {
            con = getConnection();
            pst = con.prepareStatement("select * from usuario where num_documento= " + dni + " and password=" + password + "");
            rs = pst.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getString(1));
                usuario.setDni(rs.getString(2));
                usuario.setNombre(rs.getString(3));
                usuario.setApellido(rs.getString(4));
                usuario.setCorreoElectronico(rs.getString(5));
                usuario.setContrasena(rs.getString(6));
                JOptionPane.showMessageDialog(null, "Bienvenido al Sistema " + rs.getString(3));
            } else {
                System.out.println("No se encontró Usuario con el DNI y PASSWORD ingresado");
            }
            rs.close();
            pst.close();
            con.close();
            System.out.println("SUCCESS TO VALIDATE_LOGIN - validateLogin()");
        } catch (SQLException e) {
            System.out.println("ERROR TO VALIDATE_LOGIN - validateLogin()");
        }
        return usuario;
    }
}
