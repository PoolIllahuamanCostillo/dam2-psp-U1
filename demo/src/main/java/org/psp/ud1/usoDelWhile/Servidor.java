package org.psp.ud1.usoDelWhile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args){

        try {
            ServerSocket servidor = new ServerSocket(5000);
            System.out.println("Esperando la conexión del cliente");

            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado ...");

            // CANAL QUE RECIBE LOS DATOS DEL CLIENTE
            BufferedReader recibirDatosDelCliente = new BufferedReader(
                    new InputStreamReader(
                            cliente.getInputStream()
                    )
            );

            // CANAL QUE RESPONDE LOS DATOS DESDE EL SERVIDOR PARA EL CLIENTE
            PrintWriter respondeServidor = new PrintWriter(
                    cliente.getOutputStream(), true
            );

            while (true){

                String mensaje = recibirDatosDelCliente.readLine();

                if (mensaje.equalsIgnoreCase("Salir")) {
                    System.out.println("Conexión finalizado ");

                    break;
                }

                System.out.println("Cliente: " + mensaje);
                respondeServidor.println("El servidor recibió esto: " + mensaje);
            }

            // SIEMPRE CERRAR LA CONEXION
            cliente.close();
            servidor.close();


        } catch (IOException e) {
            System.out.println("Error en el servidor");
        }
    }
}
