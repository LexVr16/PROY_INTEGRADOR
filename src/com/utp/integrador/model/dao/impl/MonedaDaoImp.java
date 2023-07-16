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
public class MonedaDaoImp extends DataBase implements MonedaDao {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    @Override
    public void insert(Moneda t) {
        try {
            con = getConnection();
            pst = con.prepareStatement("insert into moneda (idMoneda,nombre,simbolo,tipoCambio,fechaHora,idPais) values(?,?,?,?,?,?)");

            pst.setString(1, t.getId());
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
        Moneda moneda = new Moneda();
        try {
            con = getConnection();
            pst = con.prepareStatement("select * from moneda where idMoneda = '" + id + "'");

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
        List<Moneda> monedaList = new ArrayList<>();
        String sql = "select * from moneda";
        try {
            con = super.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Moneda moneda = new Moneda();

                moneda.setId(rs.getString(1));
                moneda.setNombre(rs.getString(2));
                moneda.setSimbolo(rs.getString(3));
                moneda.setTipoCambio(rs.getDouble(4));
                moneda.setFechaHora(rs.getString(5));
                moneda.setIdPais(rs.getString(6));

                monedaList.add(moneda);
            }

            rs.close();
            pst.close();
            con.close();

            System.out.println("SUCCESS TO FINDALL - findAll()");
        } catch (SQLException e) {
            System.out.println("ERROR TO FINDALL - findAll()");
            System.out.println(e);
        }

        return monedaList;

    }

    @Override
    public void update(Moneda moneda) {
        System.out.println(" <<< " + moneda.getId() + " " + moneda.getNombre()
                + " " + moneda.getSimbolo() + " " + moneda.getFechaHora() + " " + moneda.getIdPais() + " ");
        try {
            con = getConnection();
            pst = con.prepareStatement("update moneda set nombre=?, simbolo=?, "
                    + "tipoCambio=?, fechaHora=?, idPais=? where idMoneda=?");

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
            pst = con.prepareStatement("DELETE FROM Moneda WHERE idMoneda='" + id + "'");

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
    public Moneda getLastExchangeRate(String nombreMoneda) {
        Moneda moneda = new Moneda();
        try {
            con = getConnection();
            pst = con.prepareStatement("SELECT *\n"
                    + "FROM Moneda WHERE nombre = '" + nombreMoneda + "'\n"
                    + "ORDER BY STR_TO_DATE(fechaHora, '%d/%m/%Y %H:%i:%s') DESC\n"
                    + "LIMIT 1;");

            rs = pst.executeQuery();

            if (rs.next()) {
                moneda.setId(rs.getString(1));
                moneda.setNombre(rs.getString(2));
                moneda.setSimbolo(rs.getString(3));
                moneda.setTipoCambio(rs.getDouble(4));
                moneda.setFechaHora(rs.getString(5));
                moneda.setIdPais(rs.getString(6));
            } else {
                System.out.println("No hay registro ultimo tipo de Cambio de Moneda: " + nombreMoneda);
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
    public List<Moneda> getLastDistinctNombreMoneda() {
        List<Moneda> monedaList = new ArrayList<>();
        try {
            con = getConnection();
            pst = con.prepareStatement("SELECT M.idMoneda, M.nombre, M.simbolo, M.tipoCambio, M.fechaHora, M.idPais\n"
                    + "FROM Moneda M\n"
                    + "JOIN (\n"
                    + "    SELECT nombre, MAX(fechaHora) AS ultimaFechaHora\n"
                    + "    FROM Moneda\n"
                    + "    GROUP BY nombre\n"
                    + ") UltimaFecha ON M.nombre = UltimaFecha.nombre AND M.fechaHora = UltimaFecha.ultimaFechaHora;");
            rs = pst.executeQuery();

            while (rs.next()) {
                Moneda moneda = new Moneda();

                moneda.setId(rs.getString(1));
                moneda.setNombre(rs.getString(2));
                moneda.setSimbolo(rs.getString(3));
                moneda.setTipoCambio(rs.getDouble(4));
                moneda.setFechaHora(rs.getString(5));
                moneda.setIdPais(rs.getString(6));

                monedaList.add(moneda);
            }

            rs.close();
            pst.close();
            con.close();

            System.out.println("SUCCESS TO FIND - find()");
        } catch (SQLException e) {
            System.out.println("ERROR TO FIND - find()");
            System.out.println(e);
        }
        return monedaList;
    }

    @Override
    public Moneda getMonedaByNombre(String nombreMoneda) {
        Moneda moneda = new Moneda();
        try {
            con = getConnection();
            pst = con.prepareStatement("select * from moneda where nombre = '" + nombreMoneda + "'");

            rs = pst.executeQuery();

            if (rs.next()) {
                moneda.setId(rs.getString(1));
                moneda.setNombre(rs.getString(2));
                moneda.setSimbolo(rs.getString(3));
                moneda.setTipoCambio(rs.getDouble(4));
                moneda.setFechaHora(rs.getString(5));
                moneda.setIdPais(rs.getString(6));
            } else {
                System.out.println("No se encontró el Tippo de Moneda con el nombre = " + nombreMoneda);
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
}
