package com.utp.integrador.controller;

import com.utp.integrador.model.Moneda;
import com.utp.integrador.model.Pais;
import com.utp.integrador.model.dao.impl.MonedaDaoImp;
import com.utp.integrador.view.JDMoneda;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class MonedaController {

    public static JDMoneda jDMoneda = new JDMoneda(null, true);

    public static void callJDMoneda() {
        jDMoneda.setVisible(true);
    }

    public static DefaultTableModel loadJTableMonedas(DefaultTableModel defaultTableModel, JTable jTable, Moneda moneda) {
        MonedaDaoImp monedaDaoImp=new MonedaDaoImp();
        List<Moneda> listaMoneda = monedaDaoImp.findAll();
        for (Moneda moneda1 : listaMoneda) {
            Object[] objectData = {
                moneda1.getId(),
                moneda1.getNombre(),
                moneda1.getSimbolo(),
                moneda1.getTipoCambio(),
                moneda1.getFechaHora(),
                moneda1.getIdPais()
            };
            defaultTableModel.addRow(objectData);
        }

        jTable.setModel(defaultTableModel);
        return defaultTableModel;
    }

    public static DefaultTableModel loadJTableRegistraMoneda(DefaultTableModel defaultTableModel, JTable jTable, Moneda moneda) {
        Object[] objectData = {
            moneda.getId(),
            moneda.getNombre(),
            moneda.getSimbolo(),
            moneda.getTipoCambio(),
            moneda.getFechaHora(),
            moneda.getIdPais()
        };
        MonedaDaoImp monedaDaoImp=new MonedaDaoImp();
        monedaDaoImp.insert(moneda);
        defaultTableModel.addRow(objectData);
        jTable.setModel(defaultTableModel);
        JOptionPane.showMessageDialog(null, "Se registro correctamente");
        jDMoneda.jTabbedPane1.setSelectedIndex(1);
        return defaultTableModel;
    }

    public static DefaultTableModel loadJTableActualizarMoneda(DefaultTableModel defaultTableModel, JTable jTable, Moneda moneda) {

        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            if (defaultTableModel.getValueAt(i, 0).toString().equals(moneda.getId())) {
                defaultTableModel.setValueAt(moneda.getNombre(), i, 1);
                defaultTableModel.setValueAt(moneda.getSimbolo(), i, 2);
                defaultTableModel.setValueAt(moneda.getTipoCambio(), i, 3);
                defaultTableModel.setValueAt(moneda.getFechaHora(), i, 4);
                defaultTableModel.setValueAt(moneda.getIdPais(), i, 5);
                
                
                MonedaDaoImp monedaDaoImp=new MonedaDaoImp();
                monedaDaoImp.update(moneda);
                JOptionPane.showMessageDialog(null, "Se actualizó correctamente");
                jDMoneda.jTabbedPane1.setSelectedIndex(1);
            }
        }
        jTable.setModel(defaultTableModel);
        return defaultTableModel;
    }

    public static DefaultTableModel loadJTableEliminarMoneda(DefaultTableModel defaultTableModel, JTable jTable, Moneda moneda) {
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            if (defaultTableModel.getValueAt(i, 0).toString().equals(moneda.getId())) {
                defaultTableModel.removeRow(i);
                MonedaDaoImp monedaDaoImp=new MonedaDaoImp();
                monedaDaoImp.delete(moneda.getId());
                JOptionPane.showMessageDialog(null, "Se eliminó correctamente");
                jDMoneda.jTabbedPane1.setSelectedIndex(1);
            }
        }
        return defaultTableModel;
    }
}
