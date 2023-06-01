package com.utp.integrador.controller;

import com.utp.integrador.model.Pais;
import com.utp.integrador.model.dao.impl.PaisDaoImp;
import com.utp.integrador.view.JDPais;
import java.util.Arrays;
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
    
    public static DefaultTableModel loadJTablePaises(DefaultTableModel defaultTableModel, JTable jTable, Pais pais) {
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
    
    public static DefaultTableModel loadJTableRegistrarPais(DefaultTableModel defaultTableModel, JTable jTable, Pais pais) {
        PaisDaoImp paisDaoImp = new PaisDaoImp();        
        paisDaoImp.insert(pais);
        JOptionPane.showMessageDialog(null, "Se registro correctamente");
        return defaultTableModel;
    }
    
    public static DefaultTableModel loadJTableActualizarPais(DefaultTableModel defaultTableModel, JTable jTable, Pais pais) {
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            if (defaultTableModel.getValueAt(i, 0).toString().equals(pais.getId())) {
                defaultTableModel.setValueAt(pais.getNombre(), i, 1);
                JOptionPane.showMessageDialog(null, "Se actualizó correctamente");
            }
        }
        jTable.setModel(defaultTableModel);
        return defaultTableModel;
    }
    
    public static DefaultTableModel loadJTableEliminarPais(DefaultTableModel defaultTableModel, JTable jTable, Pais pais) {
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            if (defaultTableModel.getValueAt(i, 0).toString().equals(pais.getId())) {
                defaultTableModel.removeRow(i);
                JOptionPane.showMessageDialog(null, "Se eliminó correctamente");
            }
        }
        return defaultTableModel;
    }
}
