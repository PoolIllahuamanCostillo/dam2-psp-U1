package org.psp.ud1.bidireccional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

// CLIENTE CONECTA CON EL SERVIDOR
    public static void main(String[] args){

        try {
            // 1.- Conectarse al servidor
            Socket socket = new Socket("localhost", 5000);

            System.out.println("Conexión al servidor");

            // 2. Crear canal para ENVIAR datos
            PrintWriter salida = new PrintWriter(
                    socket.getOutputStream(),
                    true
            );
            // AGREGAR ESTO:
            // Canal para RECIBIR datos del servidor
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()
                    )
            );

            // 3. Enviar mensaje (Cliente escribe -> Servidor lee)
            salida.println("Hola, servidor");

            // -----------------------------------------
            // AGREGAR: Leer la respuesta del servidor (Servidor escribe -> Cliente escribe)
            String respuesta = entrada.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);
            // ----------------------------------------


            // 4.- Cerrar la conexión
            socket.close();

        } catch (Exception e){

            System.out.println("No se ha podido establecer la conexión");
        }
    }
}
