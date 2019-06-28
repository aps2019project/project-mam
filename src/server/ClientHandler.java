package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket client;

    private InputStream input;
    private OutputStream output;


    public ClientHandler(Socket client){
        this.client = client;
        try {
            input = client.getInputStream();
            output = client.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){

        }
    }
}
