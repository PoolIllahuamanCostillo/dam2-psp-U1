package org.psp.ud1.exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        // Parámetros de red
        String host = "localhost";
        int puerto = 5000;

        // Uso del try-with-resources para evitar el .close
        try (Socket socket = new Socket(host, puerto);                                       // Intenta establecer la conexión con el servidor.
             BufferedReader entrada = new BufferedReader(                                     // Canal para recibir las respuestas del servidor.
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);  // Canal para enviar texto al servidor
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conexión establecida con el servidor");

            // Bucle while para mantener la comunicación continua con el servidor
            while (true) {
                System.out.println("Escribe un mensaje (Al escribir la palabra 'salir', cortas la conexión): ");
                String mensaje = scanner.nextLine();
                // Transmite la línea escrita por el
                // usuario hacia el puerto del servidor a través del socket.
                salida.println(mensaje);

                // Recibir la respuesta del servidor
                String respuesta = entrada.readLine();
                if (respuesta != null) {
                    System.out.println("Servidor: " + respuesta);
                }

                // 1. SI ES "SALIR", ROMPEMOS EL BUCLE DEL CLIENTE
                if (mensaje.equalsIgnoreCase("salir")) {
                    System.out.println("Conexión finalizada por el cliente.");
                    break;
                }

            }
        } catch (ConnectException e) {
            System.out.println("No se puede conectar con el servidor. Comprueba que esté arrancado.");
        } catch (UnknownHostException e) {
            System.out.println("Host desconocido: " + e.getMessage());
        } catch (SocketException e) {
            System.out.println("Error de Socket: La conexión con el servidor se interrumpió.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }
    }
}
