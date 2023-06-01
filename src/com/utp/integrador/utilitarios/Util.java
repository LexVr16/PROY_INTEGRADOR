package com.utp.integrador.utilitarios;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author Usuario
 */
public class Util extends Thread {

    private final JLabel labelFechaHora;

    public Util(JLabel labelFechaHora) {
        this.labelFechaHora = labelFechaHora;
    }

    @Override
    public void run() {
        // Creamos un objeto de la clase SimpleDateFormat para formatear la fecha y hora
        SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        // Creamos un bucle infinito para actualizar la fecha y hora cada segundo
        while (true) {
            // Creamos un objeto de la clase Date para obtener la fecha y hora actual
            Date fechaHoraActual = new Date();

            // Obtenemos la fecha y hora formateada como un String
            String fechaHoraFormateada = formatoFechaHora.format(fechaHoraActual);

            // Actualizamos el texto del JLabel con la fecha y hora formateada en el hilo de la UI
            labelFechaHora.setText(fechaHoraFormateada);

            // Esperamos un segundo antes de volver a obtener la fecha y hora actual
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String generateNumComprobante(int numero) {

        int num = 0;
        num = numero + 1;

        String numGenerated = "";
        if (num < 10) {
            numGenerated = "000".concat(String.valueOf(num));
        } else if (num < 100) {
            numGenerated = "00".concat(String.valueOf(num));
        } else if (num < 1000) {
            numGenerated = "0".concat(String.valueOf(num));
        }
        return numGenerated;
    }

}

class ActualizarHoraFecha extends Thread {

    private final JLabel labelFechaHora;

    public ActualizarHoraFecha(JLabel labelFechaHora) {
        this.labelFechaHora = labelFechaHora;
    }

    @Override
    public void run() {
        // Creamos un objeto de la clase SimpleDateFormat para formatear la fecha y hora
        SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        // Creamos un bucle infinito para actualizar la fecha y hora cada segundo
        while (true) {
            // Creamos un objeto de la clase Date para obtener la fecha y hora actual
            Date fechaHoraActual = new Date();

            // Obtenemos la fecha y hora formateada como un String
            String fechaHoraFormateada = formatoFechaHora.format(fechaHoraActual);

            // Actualizamos el texto del JLabel con la fecha y hora formateada en el hilo de la UI
            labelFechaHora.setText(fechaHoraFormateada);

            // Esperamos un segundo antes de volver a obtener la fecha y hora actual
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
