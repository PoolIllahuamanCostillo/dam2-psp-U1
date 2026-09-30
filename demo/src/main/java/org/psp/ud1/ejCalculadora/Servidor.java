package org.psp.ud1.ejCalculadora;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args){

        try {

            ServerSocket servidor = new ServerSocket(5000);
            System.out.println("Servidor esperando conexión...");


            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado");

            // Crear canal para RECIBIR datos
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(
                            cliente.getInputStream()
                    )
            );

            String num1 = entrada.readLine();
            String num2 = entrada.readLine();

            System.out.println("Números recibidos: " + num1 +","+ num2);

            // Convertir a número:
            int n1 = Integer.parseInt(num1);
            int n2 = Integer.parseInt(num2);


            // Sumar
            int suma = n1 + n2;
            System.out.println("Resultado es: " + suma);

            // Canal para enviar
            PrintWriter salida = new PrintWriter(
                    cliente.getOutputStream(),
                    true);

            // Crear respuesta
            salida.println("La suma es: " + suma);


            cliente.close();
            servidor.close();


        } catch (Exception e) {
            System.out.println("No se ha podido establecer la conexión");

        }
    }

}
