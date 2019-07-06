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
    private boolean isRequested = false;
    private boolean isSeeingLive = false;
    private int gameNum;
    private ClientHandler oppHandler;

    private User user;

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
                command.handleCommand(output, this);
            else {
                System.out.println("client disconnected!: " + client.getPort());
                break;
            }
        }
    }

    public int getGameNum() {
        return gameNum;
    }

    public void setGameNum(int gameNum) {
        this.gameNum = gameNum;
    }

    public boolean isSeeingLive() {
        return isSeeingLive;
    }

    public void setSeeingLive(boolean seeingLive) {
        isSeeingLive = seeingLive;
    }

    public void setOppHandler(ClientHandler oppHandler) {
        this.oppHandler = oppHandler;
    }

    public ClientHandler getOppHandler() {
        return oppHandler;
    }

    public DataInputStream getInput() {
        return input;
    }

    public DataOutputStream getOutput() {
        return output;
    }

    public boolean isRequested() {
        return isRequested;
    }

    public void setRequested(boolean requested) {
        isRequested = requested;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Socket getClient() {
        return client;
    }
}
