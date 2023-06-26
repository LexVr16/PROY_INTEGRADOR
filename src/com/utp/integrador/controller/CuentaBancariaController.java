package com.utp.integrador.controller;

import com.utp.integrador.model.CuentaBancaria;
import com.utp.integrador.model.dao.CuentaBancariaDao;
import com.utp.integrador.model.dao.UsuarioDao;
import com.utp.integrador.model.dao.impl.CuentaBancariaDaoImp;
import com.utp.integrador.model.dao.impl.UsuarioDaoImp;
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
    
    private static JDCuentaBancaria jDCuentaBancaria = new JDCuentaBancaria(null, true);
    private static CuentaBancariaDao cuentaBancariaDao = new CuentaBancariaDaoImp();
    
    public static void callJDCuentaBancaria(String idUsuario, String dni, String fullName) {
        
        jDCuentaBancaria.txt_idUsuario.setText(idUsuario);
        jDCuentaBancaria.txt_dniUsuario.setText(dni);
        jDCuentaBancaria.txt_fullName.setText(fullName);
        jDCuentaBancaria.btn_actualizar.setEnabled(false);
        jDCuentaBancaria.btn_eliminar.setEnabled(false);
        jDCuentaBancaria.btn_seleccionar.setEnabled(false);
        jDCuentaBancaria.setVisible(true);
    }
    
    public static void callJDCuentaBancariaFromTransaccion(String dni, String nombres) {
        
        jDCuentaBancaria.txt_dniUsuario.setText(dni);
        jDCuentaBancaria.txt_fullName.setText(nombres);
        jDCuentaBancaria.btn_actualizar.setEnabled(false);
        jDCuentaBancaria.btn_eliminar.setEnabled(false);
        jDCuentaBancaria.btn_seleccionar.setEnabled(true);
        jDCuentaBancaria.jTabbedPane1.setSelectedIndex(1);
        jDCuentaBancaria.setVisible(true);
    }
    
    public static DefaultTableModel cargarTablaCuentasBancarias(DefaultTableModel defaultTableModel, JTable tablaCuenta, CuentaBancaria cuentaBancaria, String dni) {
        //obtengo las cuentas dentro de una listas de ceuntas en base al Id Usuario 
        List<CuentaBancaria> listaUsuario = getCuentasBancariasByIdUsuario(cuentaBancaria.getIdUsuario());

        //Obtenemos el DNI del Usuario mediante el IdUsuario
        String dniUsuario = getUsuarioByDNI(dni);
        System.out.println("getUsuarioByDNI " + dniUsuario);
        
        for (CuentaBancaria account : listaUsuario) {
            System.out.println("for listaUsuario " + account.getNombreCuenta());
            Object[] objectData = {
                account.getIdCuentaBancaria(),
                dniUsuario,
                account.getNombreCuenta(),
                account.getNroCuenta(),
                account.getSaldo()
            };
            defaultTableModel.addRow(objectData);
        }
        
        tablaCuenta.setModel(defaultTableModel);
        return defaultTableModel;
    }
    
    public static DefaultTableModel AgregarCuentaBancaria(DefaultTableModel defaultTableModel, JTable tablaCuenta, CuentaBancaria cuentaBancaria) {
        List<String> numerosBancarios = new ArrayList<>();
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            numerosBancarios.add(defaultTableModel.getValueAt(i, 4).toString());
        }
        
        if (!numerosBancarios.contains(cuentaBancaria.getNombreCuenta())) {
            System.out.println("el numero de cuenta es nuevo");
            Object[] objectData = {
                cuentaBancaria.getIdCuentaBancaria(),
                cuentaBancaria.getIdUsuario(),
                cuentaBancaria.getNombreCuenta(),
                cuentaBancaria.getNroCuenta(),
                cuentaBancaria.getSaldo()
            };
            defaultTableModel.addRow(objectData);
            cuentaBancariaDao.insert(cuentaBancaria);
            JOptionPane.showMessageDialog(null, "Se registro correctamente la cuenta");
            jDCuentaBancaria.jTabbedPane1.setSelectedIndex(1);
        } else {
            JOptionPane.showMessageDialog(null, "El numero de cuenta ya existe", "Atención", JOptionPane.INFORMATION_MESSAGE);
        }
        return defaultTableModel;
    }
    
    public static void actualizarCuentaBancaria(DefaultTableModel defaultTableModel, JTable tablaCuenta, CuentaBancaria cuentaBancaria) {
        System.out.println("actualizarCuentaBancaria ");
        cuentaBancariaDao.update(cuentaBancaria);
        jDCuentaBancaria.jTabbedPane1.setSelectedIndex(1);
        
    }
    
    public static DefaultTableModel eliminarCuentaBancaria(DefaultTableModel defaultTableModel, JTable tablaCuenta, CuentaBancaria cuentaBancaria) {
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            if (defaultTableModel.getValueAt(i, 0).toString().equals(cuentaBancaria.getIdCuentaBancaria())) {
                defaultTableModel.removeRow(i);
                cuentaBancariaDao.delete(cuentaBancaria.getIdCuentaBancaria());
                JOptionPane.showMessageDialog(null, "Se eliminó correctamente la cuenta");
                jDCuentaBancaria.jTabbedPane1.setSelectedIndex(1);
            }
        }
        
        return defaultTableModel;
    }
    
    private static List<CuentaBancaria> getCuentasBancariasByIdUsuario(String idCuentaBancaria) {
        System.out.println("getCuentasBancariasByIdUsuario " + idCuentaBancaria);
        //Obtenermos todas las cuentas Bancarias de la tabla
        CuentaBancariaDaoImp bancariaDaoImp = new CuentaBancariaDaoImp();
        List<CuentaBancaria> listaUsuario = bancariaDaoImp.findAllByIdUsuario(idCuentaBancaria);
        return listaUsuario;
    }
    
    private static String getUsuarioByDNI(String dni) {
        UsuarioDao usuarioDao = new UsuarioDaoImp();
        return usuarioDao.findUsuarioByDNI(dni).getDni();
    }
}
