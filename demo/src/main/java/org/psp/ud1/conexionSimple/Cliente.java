package org.psp.ud1.conexionSimple;


import java.net.Socket;


public class Cliente {

    public static void main(String[] args){

        try {
            Socket socket = new Socket("localhost", 5000);

            System.out.println("Conexión establecida correctamente");

            socket.close();
        } catch (Exception e){

            System.out.println("No se ha podido establecer la conexión");
        }
    }


}

    /*
        try {
            ServerSocket servidor = new ServerSocket(5000);

            System.out.println("Servidor iniciado.");
            System.out.println("Servidor...");

            Socket cliente = servidor.accept();

            System.out.println("Cliente conectado correctamente");

            cliente.close();
            servidor.close();

        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    */