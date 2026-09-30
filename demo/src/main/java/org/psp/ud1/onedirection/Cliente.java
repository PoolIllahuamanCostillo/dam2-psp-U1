package org.psp.ud1.onedirection;

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

            // 3. Enviar mensaje
            salida.println("Hola, servidor");

            // 4.- Cerrar la conexión
            socket.close();

        } catch (Exception e){

            System.out.println("No se ha podido establecer la conexión");
        }
    }
}
