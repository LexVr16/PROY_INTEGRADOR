package com.utp.integrador.controller;

import com.utp.integrador.model.Usuario;
import com.utp.integrador.view.JDUsuario;
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
public class UsuarioController {

    static JDUsuario jDUsuario = new JDUsuario(null, true);

    public static void callJDUsuario(String usuario) {

        if ("admin".equals(usuario)) {
            System.out.println("callJDUsuario " + usuario);
            jDUsuario.revalidate();
            jDUsuario.repaint();
            jDUsuario.setVisible(true);

        } else if (!"admin".equals(usuario)) {
            System.out.println("callJDUsuario " + usuario);
            jDUsuario.btn_actualizar.setVisible(false);
            jDUsuario.btn_eliminar.setVisible(false);

            jDUsuario.jTabbedPane.remove(1);
            jDUsuario.revalidate();
            jDUsuario.repaint();
            jDUsuario.setVisible(true);
        } else {
            System.out.println("El usuario no esta registrado");
        }
    }

    public static DefaultTableModel loadJTable_ListaUsuarios(DefaultTableModel defaultTableModel, JTable jTable, Usuario user) {
        List<Usuario> listaUsuario = Arrays.asList(
                new Usuario("0001", "12345678", "admin", "admin", "admin@gmail.com", "123"),
                new Usuario("0002", "48136550", "Cesar", "Valdiviezo", "cval@gmail.com", "123"));

        for (Usuario usuario : listaUsuario) {
            Object[] objectData = {
                usuario.getId(),
                usuario.getDni(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getCorreoElectronico(),
                usuario.getContrasena()
            };
            defaultTableModel.addRow(objectData);

        }

        jTable.setModel(defaultTableModel);
        return defaultTableModel;
    }

    public static DefaultTableModel loadJTableAgregarUsuario(DefaultTableModel defaultTableModel, JTable jTable, Usuario user) {

        List<String> listaDNI = new ArrayList<>();

        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            listaDNI.add(defaultTableModel.getValueAt(i, 1).toString());
        }

        if (listaDNI.contains(user.getDni())) {
            JOptionPane.showMessageDialog(null, "El usuario ya se encuentra registrado");
        } else {
            Object[] objectData = {
                user.getId(),
                user.getDni(),
                user.getNombre(),
                user.getApellido(),
                user.getCorreoElectronico(),
                user.getContrasena()
            };
            defaultTableModel.addRow(objectData);
            JOptionPane.showMessageDialog(null, "Se registró correctamente");
            jDUsuario.jTabbedPane.setSelectedIndex(1);
        }

        return defaultTableModel;
    }

    public static DefaultTableModel loadJTableActulizarUsuario(DefaultTableModel defaultTableModel, JTable jTable, Usuario user) {
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            if (defaultTableModel.getValueAt(i, 0).toString().equals(user.getId())) {
                defaultTableModel.setValueAt(user.getDni(), i, 1);
                defaultTableModel.setValueAt(user.getNombre(), i, 2);
                defaultTableModel.setValueAt(user.getApellido(), i, 3);
                defaultTableModel.setValueAt(user.getCorreoElectronico(), i, 4);
                defaultTableModel.setValueAt(user.getContrasena(), i, 5);

                JOptionPane.showMessageDialog(null, "Se actualizó correctamente el usuario");
                jDUsuario.jTabbedPane.setSelectedIndex(1);
            }
        }
        return defaultTableModel;
    }

    public static DefaultTableModel loadJTableEliminarUsuario(DefaultTableModel defaultTableModel, JTable jTable, Usuario user) {
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            if (defaultTableModel.getValueAt(i, 0).toString().equals(user.getId())) {
                defaultTableModel.removeRow(i);

                JOptionPane.showMessageDialog(null, "Se eliminó correctamente el usuario");
                jDUsuario.jTabbedPane.setSelectedIndex(1);
            }
        }
        return defaultTableModel;
    }
}
