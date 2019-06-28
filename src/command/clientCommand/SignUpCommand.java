package command.clientCommand;

import Model.enums.ErrorType;
import Model.user.User;
import command.CommandType;
import command.Result;
import command.ServerCommand;
import command.clientCommand.ClientCommand;
import gson.GsonWriter;

import java.io.DataOutputStream;

public class SignUpCommand extends ClientCommand {
    private String userName;
    private String pass;

    public SignUpCommand(String userName, String pass) {
        this.userName = userName;
        this.pass = pass;
        type = CommandType.SIGNUP;
    }

    @Override
    public void handleCommand(DataOutputStream output) {
        if (!userName.trim().equalsIgnoreCase("")) {
            if (User.isUserNameNew(userName)) {
                if (!pass.trim().equalsIgnoreCase("")) {
                    User.addUser(new User(userName, pass));
                    GsonWriter.sendServerCommand(new ServerCommand(CommandType.SIGNUP, User.user, Result.SUCCESSFUL), output);
                } else
                    GsonWriter.sendServerCommand(new ServerCommand(CommandType.SIGNIN, Result.FAILED, ErrorType.INVALID_PASSWORD.getMessage()), output);
            } else
                GsonWriter.sendServerCommand(new ServerCommand(CommandType.SIGNIN, Result.FAILED, ErrorType.DUPLICATE_USERNAME.getMessage()), output);
        } else
            GsonWriter.sendServerCommand(new ServerCommand(CommandType.SIGNIN, Result.FAILED, ErrorType.INVALID_USERNAME.getMessage()), output);

    }
}
