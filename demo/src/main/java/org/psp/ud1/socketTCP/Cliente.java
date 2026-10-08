package org.psp.ud1.socketTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost"; // Cambiar a la ip local (IPv4) con quien te conectarás
        int puerto = 5000; // Coincidir con el puerto del servidor
        try (
                Socket socket = new Socket(host, puerto);
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader entrada = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))
        ) {
            System.out.println("Conectado al servidor.");
            salida.println("Mensaje de prueba");
            String respuesta = entrada.readLine();
            if (respuesta != null) {
                System.out.println("Respuesta recibida: " + respuesta);
            } else {
                System.err.println("El servidor cerró la conexión sin responder.");
            }
        } catch (UnknownHostException e) {
            System.err.println("[ERROR] Dirección IP/Host no válida.");
        } catch (ConnectException e) {
            System.err.println("[ERROR] Conexión rechazada. Servidor apagado o puerto incorrecto.");
        } catch (SocketException e) {
            System.err.println("[ERROR RED] Error en socket durante transmisión.");
        } catch (IOException e) {
            System.err.println("[ERROR E/S] General: " + e.getMessage());
        }
    }
}
