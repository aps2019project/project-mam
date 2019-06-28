package server;

import Model.enums.ErrorType;
import Model.user.User;
import command.ClientCommand;
import command.CommandType;
import command.Result;
import command.ServerCommand;
import gson.GsonReader;
import gson.GsonWriter;
import org.omg.PortableInterceptor.SUCCESSFUL;

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
    public void run()  {
        while (true) {
            ClientCommand command =  GsonReader.getClientCommand(input);
            switch (command.getType()){
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
                case ENDTURN:
                case SHOWALL:
                case CREATE_GAME:
                case REQUEST_GAME:
            }
        }
    }

    public void loginAccount(String userName, String password) {
        ServerCommand command = new ServerCommand(CommandType.SIGNIN);
        if (!User.isUserNameNew(userName)) {
            if (User.isPassCorrect(userName, password)) {
                User.user = User.login(userName, password);
                command.setResult(Result.SUCCESSFUL);
                GsonWriter.sendServerCommand(new ServerCommand(CommandType.SIGNIN, Result.SUCCESSFUL), output);
            } else  GsonWriter.sendServerCommand(new ServerCommand(CommandType.SIGNIN, Result.FAILED, ErrorType.INCORRECT_PASSWORD.getMessage()), output);
        } else GsonWriter.sendServerCommand(new ServerCommand(CommandType.SIGNIN, Result.FAILED, ErrorType.INVALID_USERNAME.getMessage()), output);
    }

    public void handleCommand(){

    }


}
