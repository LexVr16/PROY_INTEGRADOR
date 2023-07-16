package com.utp.integrador.controller;

import com.utp.integrador.model.CuentaBancaria;
import com.utp.integrador.model.Moneda;
import com.utp.integrador.model.dao.CuentaBancariaDao;
import com.utp.integrador.model.dao.MonedaDao;
import com.utp.integrador.model.dao.UsuarioDao;
import com.utp.integrador.model.dao.impl.CuentaBancariaDaoImp;
import com.utp.integrador.model.dao.impl.MonedaDaoImp;
import com.utp.integrador.model.dao.impl.UsuarioDaoImp;
import com.utp.integrador.view.JDCuentaBancaria;
import java.util.List;
import javax.swing.JOptionPane;

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

    public static void callJDCuentaBancariaFromMoneda(Moneda moneda) {
        jDCuentaBancaria.txt_idMoneda.setText(moneda.getId());
        jDCuentaBancaria.txt_nombreMoneda.setText(moneda.getNombre());
    }

    public static void cargarTablaCuentasBancarias(String dni) {

        DefaultTableModel modelo;
        String[] titulo = {"Id", "DNI", "Nombre", "N° Cuenta Bancaria", "Saldo", "Moneda"};
        modelo = new DefaultTableModel(null, titulo);

        //Obtenemos el DNI del Usuario mediante el IdUsuario
        String dniUsuario = getUsuarioByDNI(dni);
        System.out.println("getUsuarioByDNI " + dniUsuario);

        //obtengo las cuentas dentro de una listas de ceuntas en base al DNI Usuario 
        List<CuentaBancaria> listaUsuario = cuentaBancariaDao.findAllAccountsByDNI(dni);
        System.out.println("findAllAccountsByDNI " + dniUsuario);

        for (CuentaBancaria account : listaUsuario) {
            //Obtenemos el nombreMoneda del Usuario mediante el IdMoneda
            String nombreMoneda = getMonedaById(account.getIdMoneda());
            System.out.println("getMonedaById " + dniUsuario);

            Object[] objectData = {
                account.getIdCuentaBancaria(),
                dniUsuario,
                account.getNombreCuenta(),
                account.getNroCuenta(),
                account.getSaldo(),
                nombreMoneda
            };
            modelo.addRow(objectData);
        }
        jDCuentaBancaria.jTable_Cuentas.setModel(modelo);

    }

    public static void AgregarCuentaBancaria(CuentaBancaria cuentaBancaria) {
        cuentaBancariaDao.insert(cuentaBancaria);
        JOptionPane.showMessageDialog(null, "Se registro correctamente la cuenta");
    }

    public static void actualizarCuentaBancaria(CuentaBancaria cuentaBancaria) {
        cuentaBancariaDao.update(cuentaBancaria);
        JOptionPane.showMessageDialog(null, "Se actualizó correctamente la cuenta");
    }

    public static void eliminarCuentaBancaria(CuentaBancaria cuentaBancaria) {
        cuentaBancariaDao.delete(cuentaBancaria.getIdCuentaBancaria());
        JOptionPane.showMessageDialog(null, "Se eliminó correctamente la cuenta");
    }

    private static String getUsuarioByDNI(String dni) {
        UsuarioDao usuarioDao = new UsuarioDaoImp();
        return usuarioDao.findUsuarioByDNI(dni).getDni();
    }

    private static String getMonedaById(String idMoneda) {
        MonedaDao usuarioDao = new MonedaDaoImp();
        return usuarioDao.find(idMoneda).getNombre();
    }
}
