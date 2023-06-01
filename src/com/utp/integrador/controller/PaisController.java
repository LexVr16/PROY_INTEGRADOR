package com.utp.integrador.controller;

import com.utp.integrador.model.Pais;
import com.utp.integrador.model.dao.impl.PaisDaoImp;
import com.utp.integrador.view.JDPais;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class PaisController {
    
    public static JDPais jDPais = new JDPais(null, true);
    
    public static void callJDPais() {
        jDPais.setVisible(true);
    }
    
    public static DefaultTableModel cargarTablaPaises(DefaultTableModel defaultTableModel, JTable jTable, Pais pais) {
        PaisDaoImp paisDaoImp = new PaisDaoImp();
        
        List<Pais> listaUsuario = paisDaoImp.findAll();
        
        for (Pais p : listaUsuario) {
            Object[] objectData = {
                p.getId(),
                p.getNombre()
            };
            defaultTableModel.addRow(objectData);
        }
        
        jTable.setModel(defaultTableModel);
        return defaultTableModel;
    }
    
    public static void registrarPais(Pais pais) {
        PaisDaoImp paisDaoImp = new PaisDaoImp();        
        paisDaoImp.insert(pais);
        JOptionPane.showMessageDialog(null, "Se registro correctamente");
    }
    
    public static void actualizarPais(Pais pais) {
        PaisDaoImp paisDaoImp = new PaisDaoImp();        
        paisDaoImp.update(pais);
    }
    
    public static void eliminarPais(Pais pais) {
        PaisDaoImp paisDaoImp = new PaisDaoImp();        
        paisDaoImp.delete(pais.getId());
    }
}
