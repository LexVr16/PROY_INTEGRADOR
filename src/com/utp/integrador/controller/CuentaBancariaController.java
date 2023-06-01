package com.utp.integrador.controller;

import com.utp.integrador.model.CuentaBancaria;
import com.utp.integrador.view.JDCuentaBancaria;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class CuentaBancariaController {

    static JDCuentaBancaria jDCuentaBancaria = new JDCuentaBancaria(null, true);

    public static void callJDCuentaBancaria(String dni, String nombres) {

        jDCuentaBancaria.txt_dni.setText(dni);
        jDCuentaBancaria.txt_fullName.setText(nombres);
        jDCuentaBancaria.btn_actualizar.setEnabled(false);
        jDCuentaBancaria.btn_eliminar.setEnabled(false);
        jDCuentaBancaria.btn_seleccionar.setEnabled(false);
        jDCuentaBancaria.setVisible(true);
    }

    public static void callJDCuentaBancariaFromTransaccion(String dni, String nombres) {

        jDCuentaBancaria.txt_dni.setText(dni);
        jDCuentaBancaria.txt_fullName.setText(nombres);
        jDCuentaBancaria.btn_actualizar.setEnabled(false);
        jDCuentaBancaria.btn_eliminar.setEnabled(false);
        jDCuentaBancaria.btn_seleccionar.setEnabled(true);
        jDCuentaBancaria.jTabbedPane1.setSelectedIndex(1);
        jDCuentaBancaria.setVisible(true);
    }

    public static DefaultTableModel loadJTableCargarCuentasBancarias(DefaultTableModel defaultTableModel, JTable tablaCuenta, CuentaBancaria cuentaBancaria) {
        //obtengo las cuentas dentro de una listas de ceuntas en base al DNI 
        List<CuentaBancaria> listaUsuario = buscarCuentasWithDNI(cuentaBancaria);
        System.out.println("cantidad de items  List<CuentaBancaria> listaUsuario) >> " + listaUsuario.size());

        //valido si el modelo de la tabla tiene filas
        if (defaultTableModel.getRowCount() > 0) {
            System.out.println("el modelo de la tabla si tiene filas " + defaultTableModel.getRowCount());

        } else {
            System.out.println("el modelo de la tabla no tiene filas " + defaultTableModel.getRowCount());
            //el modelo de la tabla no tiene filas
            for (CuentaBancaria account : listaUsuario) {
                Object[] objectData = {
                    account.getId(),
                    account.getDni(),
                    account.getNombre(),
                    account.getTipoCuenta(),
                    account.getNroDeCuenta()
                };
                defaultTableModel.addRow(objectData);
            }
        }
        tablaCuenta.setModel(defaultTableModel);
        return defaultTableModel;
    }

    public static DefaultTableModel loadTableAgregarCuentaBancaria(DefaultTableModel defaultTableModel, JTable tablaCuenta, CuentaBancaria cuentaBancaria) {
        List<String> numerosBancarios = new ArrayList<>();
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            numerosBancarios.add(defaultTableModel.getValueAt(i, 4).toString());
        }

        if (!numerosBancarios.contains(cuentaBancaria.getNroDeCuenta())) {
            System.out.println("el numero de cuenta es nuevo");
            Object[] objectData = {
                cuentaBancaria.getId(),
                cuentaBancaria.getDni(),
                cuentaBancaria.getNombre(),
                cuentaBancaria.getTipoCuenta(),
                cuentaBancaria.getNroDeCuenta()
            };
            defaultTableModel.addRow(objectData);
            JOptionPane.showMessageDialog(null, "Se registro correctamente la cuenta");
            jDCuentaBancaria.jTabbedPane1.setSelectedIndex(1);
        } else {
            JOptionPane.showMessageDialog(null, "El numero de cuenta ya existe", "Atención", JOptionPane.INFORMATION_MESSAGE);
        }
        return defaultTableModel;
    }

    public static DefaultTableModel loadTableActualizarCuentaBancaria(DefaultTableModel defaultTableModel, JTable tablaCuenta, CuentaBancaria cuentaBancaria) {
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            if (defaultTableModel.getValueAt(i, 0).toString().equals(cuentaBancaria.getId())) {
                defaultTableModel.setValueAt(cuentaBancaria.getNombre(), i, 2);
                defaultTableModel.setValueAt(cuentaBancaria.getTipoCuenta(), i, 3);
                defaultTableModel.setValueAt(cuentaBancaria.getNroDeCuenta(), i, 4);
                JOptionPane.showMessageDialog(null, "Se actualizó correctamente la cuenta");
                jDCuentaBancaria.jTabbedPane1.setSelectedIndex(1);
            }
        }

        return defaultTableModel;
    }

    public static DefaultTableModel loadTableEliminarCuentaBancaria(DefaultTableModel defaultTableModel, JTable tablaCuenta, CuentaBancaria cuentaBancaria) {
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            if (defaultTableModel.getValueAt(i, 0).toString().equals(cuentaBancaria.getId())) {
                defaultTableModel.removeRow(i);
                JOptionPane.showMessageDialog(null, "Se eliminó correctamente la cuenta");
                jDCuentaBancaria.jTabbedPane1.setSelectedIndex(1);
            }
        }

        return defaultTableModel;
    }

    private static List<CuentaBancaria> buscarCuentasWithDNI(CuentaBancaria cuentaBancaria) {
        List<CuentaBancaria> listaUsuario = Arrays.asList(
                new CuentaBancaria("0001", "admin", "Ahorro Soles", "S/.", "1111-1234-1234-1111"),
                new CuentaBancaria("0002", "admin", "Ahorro Dolares", "$.", "2222-1234-1234-2222"),
                new CuentaBancaria("0003", "48136550", "Ahorro Soles", "S/.", "3333-1234-1234-3333"),
                new CuentaBancaria("0004", "48136550", "Ahorro Dolares", "$.", "4444-1234-1234-4444"),
                new CuentaBancaria("0005", "45136550", "Ahorro Soles", "S/.", "5555-1234-1234-5555"),
                new CuentaBancaria("0006", "45136550", "Ahorro Dolares", "$.", "6666-1234-1234-66666W"));
        List<CuentaBancaria> listaUsuarioFromDNI = new ArrayList<>();
        for (CuentaBancaria account : listaUsuario) {
            if (account.getDni().equals(cuentaBancaria.getDni())) {
                listaUsuarioFromDNI.add(account);
            }
        }
        return listaUsuarioFromDNI;
    }
}
