package com.utp.integrador.controller;

import com.utp.integrador.model.Usuario;
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
        Usuario usuario = null;

        if ("admin".equals(dni) && "admin".equals(password)) {
            usuario = new Usuario();
            usuario.setId("0001");
            usuario.setDni("admin");
            usuario.setNombre("admin");
            usuario.setApellido("admin");
            usuario.setContrasena("admin");
            JOptionPane.showMessageDialog(null, "Bienvenido al Sistema " + usuario.getNombre());
        } else if ("48136550".equals(dni) && 123 == (Integer.parseInt(password))) {
            usuario = new Usuario();
            usuario.setId("0002");
            usuario.setDni(dni);
            usuario.setNombre("Cesar");
            usuario.setApellido("Valdiviezo");
            usuario.setContrasena("123");
            JOptionPane.showMessageDialog(null, "Bienvenido al Sistema " + usuario.getNombre());
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrecta, favor de volver a ingresar");
        }

        return usuario;
    }
}
