package org.psp.ud1.onedirection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args){

        try {
            // 1.- Crear el servidor en el puerto 5000
            ServerSocket servidor = new ServerSocket(5000);

            System.out.println("Servidor esperando conexión...");

            // 2. Esperar a que se conecte un cliente
            // Socket: Es un objeto que representa una conexión
            // AQUI SE DETIENE TEMPORALMENTE EL PROGRAMA HASTA QUE UN CLIENTE SE CONECTE.
            Socket cliente = servidor.accept();

            System.out.println("CLiente conectado");

            // 3. Crear canal para RECIBIR datos
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(
                            cliente.getInputStream()
                    )
            );

            // 4. Leer el mensaje enviado por el cliente
            String mensaje = entrada.readLine();

            // 5. Mostrar el mensaje
            System.out.println("Mensaje recibido: " + mensaje);

            // 6. Cerrar la conexión
            cliente.close();


        } catch (Exception e){

            System.out.println("No se ha podido establecer la conexión");
        }
    }
}
