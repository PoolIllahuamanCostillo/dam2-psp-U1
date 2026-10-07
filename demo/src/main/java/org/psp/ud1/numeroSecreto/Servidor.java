package org.psp.ud1.numeroSecreto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Servidor {

    public static void main(String[] args){

        try {
            ServerSocket servidor = new ServerSocket(5000);
            System.out.println("Esperando la conexión del cliente");

            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado ...");

            // CANAL QUE RECIBE LOS DATOS DEL CLIENTE
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(
                            cliente.getInputStream()
                    )
            );

            // CANAL QUE RESPONDE LOS DATOS DESDE EL SERVIDOR PARA EL CLIENTE
            PrintWriter salida = new PrintWriter(
                    cliente.getOutputStream(), true
            );

            // Número sercreto generado una sola vez
            Random random = new Random();
            int secreto = random.nextInt(20) + 1;
            System.out.println("Número secreto: " + secreto);


            while (true){

                int numero = Integer.parseInt(entrada.readLine());
                System.out.println("Número recibido: " + numero);

                if (numero < secreto) {
                    salida.println("MAYOR");

                } else if (numero > secreto) {
                    salida.println("MENOR");

                } else {
                    salida.println("¡CORRECTO!");
                    break;
                }
            }
            cliente.close();
            servidor.close();

        } catch (IOException e) {
            System.out.println("Error en el servidor");
        }
    }
}
