package com.utp.integrador.utilitarios;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
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

    public static String generateUniqueId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
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
