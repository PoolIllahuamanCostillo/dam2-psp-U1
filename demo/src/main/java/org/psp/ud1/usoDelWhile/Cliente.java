package org.psp.ud1.usoDelWhile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args){

        try {
            // ASEGURAMOS DE QUE EL CLIENTE SE CONECTE AL SERVIDOR
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Conexión con éxito");

            // SE CREA UN CANAL QUE ENVIE LOS DATOS AL SERVIDOR
            // (BufferedReader)
            BufferedReader enviarDatos = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()
                    )
            );

            // SE CREA UN CANAL QUE RECIBA LOS DATOS
            // QUE VENDRIA SIENDO LA RESPUESTA DEL SERVIDOR (PrinterWriter)
            PrintWriter recibirDatos = new PrintWriter(
                    socket.getOutputStream(), true
            );

            // USO DEL SCANNER PARA EL MENSAJE
            Scanner scanner = new Scanner(System.in);

            // Inicio del bucle While
                while (true){

                    System.out.println("Escribe el memsaje que se enviará al servidor: ");
                    String mensaje = scanner.nextLine();

                    recibirDatos.println(mensaje);

                    if (mensaje.equalsIgnoreCase("Salir")) {
                        break;
                    }

                    String respuesta = enviarDatos.readLine();
                    System.out.println("Servidor: " + respuesta);
                }

                socket.close();

        } catch (Exception e) {
            System.out.println("Error de conexión.");
        }
    }
}
