/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.integrador.model.dao.impl;

import com.utp.integrador.model.CuentaBancaria;
import com.utp.integrador.model.dao.CuentaBancariaDao;
import com.utp.integrador.model.dao.repository.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CuentaBancariaDaoImp extends DataBase implements CuentaBancariaDao {

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public void insert(CuentaBancaria cuentaBancaria) {
        try {
            con = getConnection();
            pst = con.prepareStatement("insert into CuentaBancaria (idCuentaBancaria, "
                    + " idUsuario, idMoneda, nombreCuenta, nroCuenta, saldo) values(?,?,?,?,?,?)");

            pst.setString(1, cuentaBancaria.getIdCuentaBancaria());
            pst.setString(2, cuentaBancaria.getIdUsuario());
            pst.setString(3, cuentaBancaria.getIdMoneda());
            pst.setString(4, cuentaBancaria.getNombreCuenta());
            pst.setString(5, cuentaBancaria.getNroCuenta());
            pst.setDouble(6, cuentaBancaria.getSaldo());

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
    public CuentaBancaria find(String idCuentaBancaria) {
        CuentaBancaria cuentaBancaria = new CuentaBancaria();

        try {
            con = getConnection();
            pst = con.prepareStatement("select * from CuentaBancaria where idCuentaBancaria= '" + idCuentaBancaria + "'");

            rs = pst.executeQuery();

            if (rs.next()) {
                cuentaBancaria.setIdCuentaBancaria(rs.getString(1));
                cuentaBancaria.setIdUsuario(rs.getString(2));
                cuentaBancaria.setIdMoneda(rs.getString(3));
                cuentaBancaria.setNombreCuenta(rs.getString(4));
                cuentaBancaria.setNroCuenta(rs.getString(5));
                cuentaBancaria.setSaldo(rs.getDouble(6));
            } else {
                System.out.println("No se encontró CuentaBancaria con el idCuentaBancaria = " + idCuentaBancaria);
            }

            rs.close();
            pst.close();
            con.close();

            System.out.println("SUCCESS TO FIND - find()");
        } catch (SQLException e) {
            System.out.println("ERROR TO FIND - find()");
            System.out.println(e);
        }
        return cuentaBancaria;
    }

    @Override
    public List<CuentaBancaria> findAll() {
        List<CuentaBancaria> cuentaBancariaList = new ArrayList<>();

        try {
            con = getConnection();
            pst = con.prepareStatement("SELECT * FROM CuentaBancaria");
            rs = pst.executeQuery();

            while (rs.next()) {
                CuentaBancaria cuentaBancaria = new CuentaBancaria();
                cuentaBancaria.setIdCuentaBancaria(rs.getString(1));
                cuentaBancaria.setIdUsuario(rs.getString(2));
                cuentaBancaria.setNombreCuenta(rs.getString(3));
                cuentaBancaria.setNroCuenta(rs.getString(4));
                cuentaBancaria.setSaldo(rs.getDouble(5));

                cuentaBancariaList.add(cuentaBancaria);
            }

            rs.close();
            pst.close();
            con.close();

            System.out.println("SUCCESS TO FINDALL - findAll()");
        } catch (SQLException e) {
            System.out.println("ERROR TO FINDALL - findAll()");
            System.out.println(e);
        }
        return cuentaBancariaList;
    }

    @Override
    public void update(CuentaBancaria cuentaBancaria) {
        try {
            con = getConnection();
            pst = con.prepareStatement("UPDATE CuentaBancaria SET idUsuario=?,"
                    + " nombreCuenta=?, nroCuenta=?, saldo=? WHERE idCuentaBancaria=?");

            pst.setString(1, cuentaBancaria.getIdUsuario());
            pst.setString(2, cuentaBancaria.getNombreCuenta());
            pst.setString(3, cuentaBancaria.getNroCuenta());
            pst.setString(4, String.valueOf(cuentaBancaria.getSaldo()));

            pst.setString(5, cuentaBancaria.getIdCuentaBancaria());

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
    public void delete(String idCuentaBancaria) {
        try {
            con = this.getConnection();
            pst = con.prepareStatement("DELETE FROM CuentaBancaria WHERE idCuentaBancaria=?");

            pst.setString(1, idCuentaBancaria);

            pst.executeUpdate();

            pst.close();
            con.close();

            System.out.println("SUCCESS TO DELETE - delete()");
        } catch (SQLException e) {
            System.out.println("ERROR TO DELETE - delete()");
            System.out.println(e);
        }
    }

    @Override
    public List<CuentaBancaria> findAllAccountsByDNI(String dni) {
        List<CuentaBancaria> cuentaBancariaList = new ArrayList<>();

        try {
            con = getConnection();
//            pst = con.prepareStatement("SELECT * FROM CuentaBancaria WHERE dni = '" + idUsuario + "'");
            pst = con.prepareStatement("SELECT cb.* FROM CuentaBancaria cb JOIN Usuario u ON "
                    + "cb.idUsuario = u.idUsuario WHERE u.dni = '" + dni + "'");
            rs = pst.executeQuery();

            while (rs.next()) {
                CuentaBancaria cuentaBancaria = new CuentaBancaria();
                cuentaBancaria.setIdCuentaBancaria(rs.getString(1));
                cuentaBancaria.setIdUsuario(rs.getString(2));
                cuentaBancaria.setIdMoneda(rs.getString(3));
                cuentaBancaria.setNombreCuenta(rs.getString(4));
                cuentaBancaria.setNroCuenta(rs.getString(5));
                cuentaBancaria.setSaldo(rs.getDouble(6));

                cuentaBancariaList.add(cuentaBancaria);
            }

            rs.close();
            pst.close();
            con.close();

            System.out.println("SUCCESS TO FINDALL - findAllByIdUsuario()");
        } catch (SQLException e) {
            System.out.println("ERROR TO FINDALL - findAllByIdUsuario()");
            System.out.println(e);
        }
        return cuentaBancariaList;
    }

    @Override
    public void updateSaldo(CuentaBancaria cuentaBancaria) {
        try {
            con = getConnection();
            pst = con.prepareStatement("UPDATE CuentaBancaria SET saldo=? "
                    + "WHERE idCuentaBancaria=?");

            pst.setString(1, String.valueOf(cuentaBancaria.getSaldo()));
            pst.setString(2, cuentaBancaria.getIdCuentaBancaria());

            pst.executeUpdate();

            pst.close();
            con.close();

            System.out.println("SUCCESS TO UPDATE - update()");
        } catch (SQLException e) {
            System.out.println("ERROR TO UPDATE - update()");
            System.out.println(e);
        }
    }

}
