package com.utp.integrador.controller;

import com.utp.integrador.model.Usuario;
import com.utp.integrador.model.dao.UsuarioDao;
import com.utp.integrador.model.dao.impl.UsuarioDaoImp;
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
    
    public static DefaultTableModel cargarListaUsuarios(DefaultTableModel defaultTableModel, JTable jTable, Usuario user) {
        UsuarioDaoImp usuarioDaoImp = new UsuarioDaoImp();
        List<Usuario> listaUsuario = usuarioDaoImp.findAll();
        
        for (Usuario usuario : listaUsuario) {
            Object[] objectData = {
                usuario.getIdUsuario(),
                usuario.getDni(),
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getEmail(),
                usuario.getPassword()
            };
            defaultTableModel.addRow(objectData);
            
        }
        
        jTable.setModel(defaultTableModel);
        return defaultTableModel;
    }
    
    public static boolean registrarUsuario(Usuario user) {
        boolean registrado = false;
        
        UsuarioDaoImp usuarioDaoImp = new UsuarioDaoImp();
        List<Usuario> listaUsuario = usuarioDaoImp.findAll();
        
        List<String> listaDNI = new ArrayList<>();
        
        for (int i = 0; i < listaUsuario.size(); i++) {
            listaDNI.add(listaUsuario.get(i).getDni());
        }
        
        if (listaDNI.contains(user.getDni())) {
            JOptionPane.showMessageDialog(null, "El usuario ya se encuentra registrado");
        } else {
            usuarioDaoImp.insert(user);
            JOptionPane.showMessageDialog(null, "Se registró correctamente");
            jDUsuario.jTabbedPane.setSelectedIndex(1);
            registrado = true;
        }
        return registrado;
    }
    
    public static void actulizarUsuario(Usuario user) {
        UsuarioDaoImp usuarioDaoImp = new UsuarioDaoImp();
        usuarioDaoImp.update(user); 
    }
    
    public static void eliminarUsuario(Usuario user) {
        UsuarioDaoImp usuarioDaoImp = new UsuarioDaoImp();
        usuarioDaoImp.delete(user.getIdUsuario()); 
    }
}
