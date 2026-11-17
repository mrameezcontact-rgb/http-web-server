package webserver;

import webserver.handler.RequestHandler;

import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    static final int PORT = 8888;

    public static void main(String[] args) {

        try (ServerSocket serSocket = new ServerSocket(PORT)) {
            System.out.println("SERVER STARTED AT PORT: " + PORT + "\n");

            while (true) {
                Socket sock = serSocket.accept();
                new RequestHandler(sock).start();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}