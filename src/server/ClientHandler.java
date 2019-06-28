package server;

import Model.enums.ErrorType;
import Model.user.User;
import command.ClientCommand;
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
                switch (command.getType()) {
                    case BUY:
                    case MOVE:
                    case SAVE:
                    case SELL:
                    case ATTACK:
                    case INSERT:
                    case SEARCH:
                    case SIGNIN:
                        loginAccount(command.getUserName(), command.getPass());
                    case SIGNUP:
                        createAccount(command.getUserName(), command.getPass());
                    case ENDTURN:
                    case SHOWALL:
                    case CREATE_GAME:
                    case REQUEST_GAME:
                }
            else {
                System.out.println("client disconnected!");
                break;
            }
        }
    }

    public void createAccount(String userName, String password) {
        if (!userName.trim().equalsIgnoreCase("")) {
            if (User.isUserNameNew(userName)) {
                if (!password.trim().equalsIgnoreCase("")) {
                    User.addUser(new User(userName, password));
                    GsonWriter.sendServerCommand(new ServerCommand(CommandType.SIGNUP, User.user, Result.SUCCESSFUL), output);
                } else
                    GsonWriter.sendServerCommand(new ServerCommand(CommandType.SIGNIN, Result.FAILED, ErrorType.INVALID_PASSWORD.getMessage()), output);
            } else
                GsonWriter.sendServerCommand(new ServerCommand(CommandType.SIGNIN, Result.FAILED, ErrorType.DUPLICATE_USERNAME.getMessage()), output);
        } else
            GsonWriter.sendServerCommand(new ServerCommand(CommandType.SIGNIN, Result.FAILED, ErrorType.INVALID_USERNAME.getMessage()), output);
    }

    public void loginAccount(String userName, String password) {
        if (!User.isUserNameNew(userName)) {
            if (User.isPassCorrect(userName, password)) {
                User.user = User.login(userName, password);
                GsonWriter.sendServerCommand(new ServerCommand(CommandType.SIGNIN, User.user, Result.SUCCESSFUL), output);
            } else
                GsonWriter.sendServerCommand(new ServerCommand(CommandType.SIGNIN, Result.FAILED, ErrorType.INCORRECT_PASSWORD.getMessage()), output);
        } else
            GsonWriter.sendServerCommand(new ServerCommand(CommandType.SIGNIN, Result.FAILED, ErrorType.INVALID_USERNAME.getMessage()), output);
    }

    public void handleCommand() {

    }


}
