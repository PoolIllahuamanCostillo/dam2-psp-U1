package org.psp.ud1.modulo2.ejercicioUDP.ex1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Servidor {
    public static void main(String[] args){
        try(DatagramSocket socket = new DatagramSocket(5000)){
            System.out.print("servidor UDP esperando");
            while(true){
                byte[] buffer =new byte[1024];
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                try{
                    socket.receive(paquete);
                    String mensaje= new String(
                            paquete.getData(),
                            0,
                            paquete.getLength()
                    );
                    System.out.println("mensaje recibido correctamente");

                }catch (IOException e){
                    System.out.println("error al recibir el paquete"+ e.getMessage());
                }

            }

        }catch (SocketException e){
            System.out.println("error critico:El puerto esta en uso");
        }
    }
}