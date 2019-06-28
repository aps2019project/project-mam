package command.clientCommand;

import command.CommandType;
import command.clientCommand.ClientCommand;

public class SignInCommand extends ClientCommand {
    protected String userName;
    protected String pass;

    public SignInCommand(String userName, String pass) {
        this.userName = userName;
        this.pass = pass;
        type = CommandType.SIGNIN;
    }

    public void handleCommand(){

    }
}
