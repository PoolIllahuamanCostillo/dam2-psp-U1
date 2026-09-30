package org.psp.ud1.ejCalculadora;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteCalculadora {

    public static void main(String[] args){

        try {
            // Conexión al servidor
            Socket socket = new Socket("localhost",5000);

            System.out.println("Conexión establecida correctamente");

            // Canal de salida con autoflush
            PrintWriter salida = new PrintWriter(
                    socket.getOutputStream(), true);

            // Enviar dos números
            salida.println("5");
            salida.println("3");

            // Canal de entrada para recibir la respuesta:
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()
                    )
            );

            // Leer el respuesta
            String respuesta = entrada.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);

            // Cerrar la conexión
            socket.close();
        } catch (Exception e) {
            System.out.println("No se ha podido establecer la conexión");;
        }
    }
}
