package org.psp.ud1.numeroSecreto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        try {
            // Conexión con el server
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Conexión con éxito");

            // Canal para recibir datos -> BufferedReader
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()
                    )
            );

            // Canal para enviar datos -> PrintWriter
            // QUE VENDRIA SIENDO LA RESPUESTA DEL SERVIDOR
            PrintWriter salida = new PrintWriter(
                    socket.getOutputStream(), true
            );

            // Lectura del teclado
            Scanner scanner = new Scanner(System.in);

            // Inicio del bucle While
            while (true) {

                System.out.println("Escribe un número del 1 al 20: ");
                String numero = scanner.nextLine();

                // Enviar el número al servidor
                salida.println(numero);

                // Recibir la pista:
                String respuesta = entrada.readLine();
                System.out.println("Servidor: " + respuesta);

                // Si acierta, termina el bucle
                if (respuesta.equalsIgnoreCase("¡CORRECTO!")) {
                    break;
                }

            }

            socket.close();

        } catch (Exception e) {
            System.out.println("Error de conexión.");
        }
    }
}
