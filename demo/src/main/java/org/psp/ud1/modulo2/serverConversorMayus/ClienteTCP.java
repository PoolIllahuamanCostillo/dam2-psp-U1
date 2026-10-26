package org.psp.ud1.modulo2.serverConversorMayus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTCP {

    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try (Socket socket = new Socket(host, puerto);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conexión establecida con el servidor conversor.");
            System.out.print("Escribe una frase para convertir a mayúsculas ('salir' para terminar): ");

            String frase = scanner.nextLine();

            // Envía la frase asegurando el salto de línea con println
            salida.println(frase);

            if (!frase.equalsIgnoreCase("salir")) {
                // Recibe la respuesta procesada del servidor
                String respuestaMayus = entrada.readLine();
                System.out.println("Respuesta del Servidor: " + respuestaMayus);
            }

        } catch (ConnectException e) {
            System.out.println("Error: No se pudo conectar con el Servidor. ¿Está encendido en el puerto " + puerto + "?");
        } catch (IOException e) {
            System.out.println("Error de comunicación: " + e.getMessage());
        }
    }

}
