package org.psp.ud1.modulo2.ejercicioUDP.ex2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor {
    public static void main(String[] args) throws Exception {
        try (DatagramSocket socket = new DatagramSocket(5000)) {
            System.out.println("Servidor UDP esperando...");

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);

                try {
                    socket.receive(paquete);
                    String mensaje = new String(
                            paquete.getData(),
                            0,
                            paquete.getLength()
                    );

                    System.out.println("Mensaje recibido: " + mensaje);

                    // RESPONDE
                    String responda = "Soy el servidor gracias por tu mensaje";
                    byte[] datosmensaje = responda.getBytes();

                    DatagramPacket paqueteresponda = new DatagramPacket(
                            datosmensaje,
                            datosmensaje.length,
                            paquete.getAddress(),
                            paquete.getPort()
                    );

                    socket.send(paqueteresponda);
                    socket.close();

                } catch (Exception e) {
                    System.out.println("Error al recibir el paquete: " + e.getMessage());
                }
            }
        }
    }
}
