package org.psp.ud1.modulo2.ejercicioUDP.ex1;

import java.io.IOException;
import java.net.*;

public class Cliente {
    public static void main(String[] args) throws Exception{
        try(DatagramSocket socket = new DatagramSocket()){
            InetAddress ipServidor= InetAddress.getByName("localhost");
            int puerto = 5000;
            String mensaje = "hola servidor";
            byte[] buffer = mensaje.getBytes();
            DatagramPacket paquete = new DatagramPacket(
                    buffer,
                    buffer.length,
                    ipServidor,
                    puerto
            );

            socket.send(paquete);
            System.out.println("mensaje enviado con exito");
        }catch (UnknownHostException e){
            System.out.println("error: no encontro la ip del servidor");
            e.printStackTrace();
        }catch (SocketException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}