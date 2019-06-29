package command.clientCommand;

import Model.enums.ErrorType;
import Model.user.User;
import command.CommandType;
import command.Result;
import command.ServerCommand;
import command.clientCommand.ClientCommand;
import static command.CommandType.*;
import gson.GsonWriter;
import server.ClientHandler;
import server.Server;

import java.io.DataOutputStream;

public class SignInCmd extends ClientCommand {
    protected String userName;
    protected String pass;

    public SignInCmd(String userName, String pass) {
        this.userName = userName;
        this.pass = pass;
        type = SIGNIN;
    }

    public void handleCommand(DataOutputStream output, ClientHandler handler){
        if (!User.isUserNameNew(userName)) {
            if (User.isPassCorrect(userName, pass)) {
                handler.setUser(User.login(userName, pass));
                Server.getClients().put(userName, handler);
                GsonWriter.sendServerCommand(new ServerCommand(CommandType.SIGNIN, handler.getUser(), Result.SUCCESSFUL), output);
            } else
                GsonWriter.sendServerCommand(new ServerCommand(CommandType.SIGNIN, Result.FAILED, ErrorType.INCORRECT_PASSWORD.getMessage()), output);
        } else
            GsonWriter.sendServerCommand(new ServerCommand(CommandType.SIGNIN, Result.FAILED, ErrorType.INVALID_USERNAME.getMessage()), output);
    }
}
