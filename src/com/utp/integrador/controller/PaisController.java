package com.utp.integrador.controller;

import com.utp.integrador.model.Pais;
import com.utp.integrador.model.dao.PaisDao;
import com.utp.integrador.model.dao.impl.PaisDaoImp;
import com.utp.integrador.view.JDPais;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class PaisController {

    public static final JDPais jDPais = new JDPais(null, true);
    public static final PaisDao paisDao = new PaisDaoImp();

    public static void callJDPais() {
        jDPais.setVisible(true);
    }

    public static void cargarPaisesEnLaTabla() {
        DefaultTableModel modelo;
        String[] titulo = {"Cod", "Nombre"};
        modelo = new DefaultTableModel(null, titulo);
        
        List<Pais> paises = paisDao.findAll();

        for (Pais pais : paises) {
            Object[] objectData = {
                pais.getId(),
                pais.getNombre()
            };
            modelo.addRow(objectData);
        }
        jDPais.jTable_Pais.setModel(modelo);
    }

    public static void registrarPais(Pais pais) {
        paisDao.insert(pais);
        JOptionPane.showMessageDialog(null, "Se registró correctamente");
    }

    public static void actualizarPais(Pais pais) {
        paisDao.update(pais);
        JOptionPane.showMessageDialog(null, "Se actualizó correctamente");
    }

    public static void eliminarPais(Pais pais) {
        paisDao.delete(pais.getId());
        JOptionPane.showMessageDialog(null, "Se eliminó correctamente");
    }
}
