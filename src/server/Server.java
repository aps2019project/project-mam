package server;

import gson.GsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static int port = 8000;

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("server created");
            initServer();
            while (true){
                Socket client = server.accept();
                System.out.println("a new client connected");
                new ClientHandler(client).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initServer() throws FileNotFoundException {
        GsonReader.readUser();
        GsonReader.initShop();
    }
}