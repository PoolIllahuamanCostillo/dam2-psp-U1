package org.psp.ud1.modulo2.serverConversorMayus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorTCP {

    public static void main(String[] args) {

        int puerto = 6000;

        // 1. try-with-resources para asegurar que el ServerSocket se libere automáticamente
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("El servidor está esperando la conexión en el puerto " + puerto + "...");

            // BUCLE EXTERNO: Mantiene el servidor encendido para atender a múltiples clientes uno tras otro
            while (true) {

                // El servidor se queda bloqueado aquí esperando a que un cliente se conecte
                try (Socket cliente = servidor.accept()) {
                    System.out.println("\n¡Cliente conectado desde: " + cliente.getInetAddress().getHostAddress() + "!");

                    // Canal que RECIBE datos del cliente
                    BufferedReader entrada = new BufferedReader(
                            new InputStreamReader(cliente.getInputStream())
                    );

                    // Canal que ENVÍA datos al cliente (true para autoflush)
                    PrintWriter salida = new PrintWriter(
                            cliente.getOutputStream(), true
                    );

                    // BUCLE INTERNO: Atiende los mensajes del cliente actual
                    while (true) {
                        String mensaje = entrada.readLine();

                        // Si el cliente se desconecta o envía "salir"
                        if (mensaje == null || mensaje.equalsIgnoreCase("salir")) {
                            System.out.println("El cliente ha finalizado la comunicación.");
                            break;
                        }

                        System.out.println("Frase recibida del cliente: " + mensaje);

                        // Procesar la frase: Transformarla completamente a MAYÚSCULAS
                        String mensajeMayusculas = mensaje.toUpperCase();

                        // Devolver el resultado procesado al cliente
                        salida.println(mensajeMayusculas);
                        System.out.println("Enviado al cliente en mayúsculas: " + mensajeMayusculas);
                    }

                } catch (IOException e) {
                    System.out.println("Error en la comunicación con el cliente actual: " + e.getMessage());
                }

                System.out.println("Esperando al siguiente cliente...");
            }

        } catch (BindException e) {
            // Excepción específica si el puerto 6000 ya está ocupado por otra aplicación
            System.out.println("Error crítico: El puerto " + puerto + " ya está en uso por otra aplicación.");
        } catch (IOException e) {
            System.out.println("Error de E/S en el servidor: " + e.getMessage());
        }
    }
}
