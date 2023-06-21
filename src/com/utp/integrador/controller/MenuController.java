package com.utp.integrador.controller;

import com.utp.integrador.model.Usuario;
import com.utp.integrador.view.FrmMenu;

/**
 *
 * @author Usuario
 */
public class MenuController {
    
    public static void callFrmMenu(Usuario usuario) {
        if ("admin".equals(usuario.getDni())) {
            FrmMenu frmMenu = new FrmMenu();
            frmMenu.lbl_dni.setText(String.valueOf(usuario.getDni()));
            frmMenu.lbl_usuario.setText(usuario.getNombres().concat(" ").concat(usuario.getApellidos()));
            frmMenu.menu_registrar.setVisible(false);
            frmMenu.setVisible(true);
        } else {
            FrmMenu frmMenu = new FrmMenu();
            frmMenu.lbl_dni.setText(String.valueOf(usuario.getDni()));
            frmMenu.lbl_usuario.setText(usuario.getNombres().concat(" ").concat(usuario.getApellidos()));
            frmMenu.men_mantenimiento.setVisible(false);
            frmMenu.setVisible(true);
        }
    }
}
