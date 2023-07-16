package com.utp.integrador.controller;

import com.utp.integrador.model.Moneda;
import com.utp.integrador.model.dao.PaisDao;
import com.utp.integrador.model.dao.impl.MonedaDaoImp;
import com.utp.integrador.model.dao.impl.PaisDaoImp;
import com.utp.integrador.view.JDMoneda;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class MonedaController {

    private static final JDMoneda jDMoneda = new JDMoneda(null, true);
    private static final MonedaDaoImp monedaDaoImp = new MonedaDaoImp();

    public static void callJDMoneda() {
        jDMoneda.btn_seleccionar.setEnabled(false);
        jDMoneda.setVisible(true);

    }

    public static void callJDMonedaFromCuentaBancaria() {
        jDMoneda.btn_seleccionar.setEnabled(true);
        jDMoneda.setVisible(true);
    }

    public static void cargarMonedasEnLaTabla() {
        DefaultTableModel modelo;
        String[] titulo = {"Id", "Nombre", "Simbolo", "Tipo de Cambio", "Fecha y Hora", "Pais"};
        modelo = new DefaultTableModel(null, titulo);
               
        //Obtenemos todos los registros de la tabla Moneda en la BD
        List<Moneda> listaMoneda = monedaDaoImp.findAll();

        for (Moneda coin : listaMoneda) {
            //Obtenemos el nombreMoneda del Usuario mediante el IdMoneda
            String nombrePais = getNombrePaisById(coin.getIdPais());
            System.out.println("getNombrePaisById " + coin.getIdPais());
            
            Object[] objectData = {
                coin.getId(),
                coin.getNombre(),
                coin.getSimbolo(),
                coin.getTipoCambio(),
                coin.getFechaHora(),
                nombrePais
            };
            modelo.addRow(objectData);
        }
        jDMoneda.jTable_moneda.setModel(modelo);
    }

    public static void RegistraMoneda(Moneda moneda) {
        monedaDaoImp.insert(moneda);
        JOptionPane.showMessageDialog(null, "Se registro correctamente la Moneda");

    }

    public static void ActualizarMoneda(Moneda moneda) {
        monedaDaoImp.update(moneda);
        JOptionPane.showMessageDialog(null, "Se registro correctamente la Moneda");
    }

    public static void EliminarMoneda(Moneda moneda) {
        monedaDaoImp.delete(moneda.getId());
        JOptionPane.showMessageDialog(null, "Se registro correctamente la Moneda");
    }
    
    private static String getNombrePaisById(String idPais) {
        PaisDao paisDao = new PaisDaoImp();
        return paisDao.find(idPais).getNombre();
    }
}
