package com.utp.integrador.controller;

import com.utp.integrador.model.CuentaBancaria;
import com.utp.integrador.model.Transaccion;
import com.utp.integrador.model.Usuario;
import com.utp.integrador.model.dao.MonedaDao;
import com.utp.integrador.model.dao.TransaccionDao;
import com.utp.integrador.model.dao.UsuarioDao;
import com.utp.integrador.model.dao.impl.MonedaDaoImp;
import com.utp.integrador.model.dao.impl.TransaccionDaoImp;
import com.utp.integrador.model.dao.impl.UsuarioDaoImp;
import com.utp.integrador.view.JDTransaccion;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class TransaccionController {

    static JDTransaccion jDTransaccion = new JDTransaccion(null, true);

    public static void callJDTransaccion(String dni, String usuario) {

        jDTransaccion.lbl_dni.setText(dni);
        jDTransaccion.lbl_user.setText(usuario);
        jDTransaccion.setVisible(true);
    }

    public static void callJDTransaccionFromAccont(CuentaBancaria account) {
        String simboloMoneda = getSimboloMonedaById(account.getIdMoneda());

        if (jDTransaccion.jRadioBtn_retiro.isSelected()) {
            
            if (jDTransaccion.lbl_simboloCambio.getText().equals(simboloMoneda)) {
                jDTransaccion.txt_idCuentaBancaria1.setText(account.getIdCuentaBancaria());
                jDTransaccion.lbl_simboloOrign.setText(simboloMoneda);
                jDTransaccion.txt_accountOrigen.setText(account.getNroCuenta());
                jDTransaccion.setVisible(true);
            }
        } else if (jDTransaccion.jRadioBtn_transferencia.isSelected()) {
            
            if (jDTransaccion.lbl_simboloCambio.getText().equals(simboloMoneda)) {
                jDTransaccion.txt_idCuentaBancaria1.setText(account.getIdCuentaBancaria());
                jDTransaccion.lbl_simboloOrign.setText(simboloMoneda);
                jDTransaccion.txt_accountOrigen.setText(account.getNroCuenta());              
            } else {
                jDTransaccion.txt_idCuentaBancaria2.setText(account.getIdCuentaBancaria());
                jDTransaccion.lbl_simboloDestiny.setText(simboloMoneda);
                jDTransaccion.txt_accountDestino.setText(account.getNroCuenta());               
            }
             jDTransaccion.setVisible(true);
        }
    }

    private static String getSimboloMonedaById(String idMoneda) {
        MonedaDao monedaDao = new MonedaDaoImp();
        return monedaDao.find(idMoneda).getSimbolo();
    }

    public static void insertTransaccion(Transaccion transaccion) {
        TransaccionDao transaccionDao = new TransaccionDaoImp();
        transaccionDao.insert(transaccion);
        JOptionPane.showMessageDialog(null, "Se registro correctamente la Transaccion");
    }
}
