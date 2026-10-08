package org.psp.ud1.exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

// Clase
public class Servidor {
    // Método
    public static void main(String[] args) {

        //Definición del puerto en el que el servidor escuchará las peticiones
        int puerto = 5000;

        // Uso de try-with-resources sin requerir .close()
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor esperando la conexión en el puerto: " + puerto + "...");

            try (Socket cliente = servidor.accept();
                 BufferedReader entrada = new BufferedReader(
                         new InputStreamReader(cliente.getInputStream()));
                 PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true)) {

                System.out.println("Cliente conectado desde: " + cliente.getInetAddress().getHostAddress());

                // 2. Mantener la conexión abierta (Bucle)
                while (true) {
                    String mensaje = entrada.readLine();

                    // Si el cliente manda el mensaje "salir", cierra la conexión
                    if (mensaje == null || mensaje.equalsIgnoreCase("salir")) {
                        System.out.println("El cliente ha finalizado la comunicación");
                        salida.println("Conexión cerrada por el servidor");
                        break;
                    }

                    System.out.println("Cliente dice: " + mensaje);
                    salida.println("Servidor recibió: " + mensaje);

                }
            }

        } catch (SocketException e) {
            System.out.println("Error de Socket: El cliente se ha desconectado de forma inesperada.");
        } catch (IOException e) {
            System.out.println("Error de E/S en el servidor: " + e.getMessage());
        }
    }
}
