package server;

import gson.GsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {

    private static int port = 8000;

    private static HashMap<String, ClientHandler> clients = new HashMap<>();

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("server started.");
            initServer();
            while (true){
                Socket client = server.accept();
                System.out.println("a new client connected: " + client.getInetAddress()+" / "+client.getPort());
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

    public static HashMap<String, ClientHandler> getClients() {
        return clients;
    }
}
