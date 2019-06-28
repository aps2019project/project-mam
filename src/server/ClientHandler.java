package server;

import Model.enums.ErrorType;
import Model.user.User;
import command.clientCommand.ClientCommand;
import command.CommandType;
import command.Result;
import command.ServerCommand;
import gson.GsonReader;
import gson.GsonWriter;
import view.pages.Page;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket client;

    private DataInputStream input;
    private DataOutputStream output;


    public ClientHandler(Socket client) {
        this.client = client;
        try {
            input = new DataInputStream(client.getInputStream());
            output = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            ClientCommand command = GsonReader.getClientCommand(input);
            if (command != null)
                command.handleCommand(output);
            else {
                System.out.println("client disconnected!");
                break;
            }
        }
    }
}
