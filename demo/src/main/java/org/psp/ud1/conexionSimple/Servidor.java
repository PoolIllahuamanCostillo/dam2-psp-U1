package org.psp.ud1.conexionSimple;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args){

        try {
            ServerSocket servidor = new ServerSocket(5000);

            System.out.println("Servidor iniciado. ");
            System.out.println("Esperando cliente... ");

            Socket cliente = servidor.accept();

            System.out.println("Cliente conectado correctamente");

            cliente.close();
            servidor.close();

        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }


        /*
        try {
            Socket = new ServerSocket(5000);

            System.out.println("Servidor iniciado: ");
            System.out.println("Servidor: ");

            Socket cliente = servidor.accept();

            System.out.println("Cliente conectado correctamente");

            cliente.close();
            servidor.close();

        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    */
}
