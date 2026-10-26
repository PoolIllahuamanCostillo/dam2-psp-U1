package org.psp.ud1.modulo2.ejercicioUDP.ex2;
import java.net.*;

public class Cliente {
    public static void main(String[] args) throws Exception {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress direccion = InetAddress.getByName("localhost");
            int puerto = 5000;
            String mensaje = "hola servidor";
            byte[] buffer = mensaje.getBytes();

            DatagramPacket paquete = new DatagramPacket(
                    buffer,
                    buffer.length,
                    direccion,
                    puerto
            );

            socket.send(paquete);
            System.out.println("Mensaje enviado con la informacion");

            // Recibir mensaje del servidor (Respuesta)
            byte[] bufferServer = new byte[1024];
            DatagramPacket paqueteServer = new DatagramPacket(
                    bufferServer,
                    bufferServer.length
            );

            socket.receive(paqueteServer);

            String mensajeServer = new String(
                    paqueteServer.getData(),
                    0,
                    paqueteServer.getLength()
            );

            System.out.println("Respuesta Servidor: " + mensajeServer);
            socket.close();
        }
    }
}