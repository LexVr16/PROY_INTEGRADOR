package com.utp.integrador.controller;

import com.utp.integrador.model.CuentaBancaria;
import com.utp.integrador.view.JDTransaccion;

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
        jDTransaccion.setVisible(true);
//        if (account.getTipoCuenta().equals("S/.")) {
//            jDTransaccion.lbl_tipoCambio1.setText(account.getTipoCuenta());
//            jDTransaccion.txt_accountOrigen.setText(account.getNroDeCuenta());
//            jDTransaccion.setVisible(true);
//        } else {
//            jDTransaccion.lbl_tipoCambio2.setText(account.getTipoCuenta());
//            jDTransaccion.txt_accountDestino.setText(account.getNroDeCuenta());
//            jDTransaccion.setVisible(true);
//        }

    }

}
