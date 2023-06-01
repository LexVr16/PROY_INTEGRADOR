package com.utp.integrador.controller;

import com.utp.integrador.model.Usuario;
import com.utp.integrador.model.dao.impl.LoginDaoImp;
import com.utp.integrador.view.FrmLogin;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class LoginController {

    public static void callFrmLogin() {
        FrmLogin frmLogin = new FrmLogin();
        frmLogin.setVisible(true);
    }

    //recibe el dni, password, objeto frmLogin
    public static Usuario validateFrmLogin(String dni, String password) {
        //creo objeto login
        LoginDaoImp login = new LoginDaoImp();

        //valida el dni y password en el metodo validateLogin y devuelve el usuario
        Usuario usuario = login.validateLogin(dni, password);

        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "No se encontró Usuario, vuelva a intentarlo");
        } 

       
        return usuario;
    }
}
