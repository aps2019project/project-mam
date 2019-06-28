package command.clientCommand;

import command.CommandType;
import command.clientCommand.ClientCommand;

public class SignUpCommand extends ClientCommand {
    private String userName;
    private String pass;

    public SignUpCommand(String userName, String pass) {
        this.userName = userName;
        this.pass = pass;
        type = CommandType.SIGNUP;
    }

    public void handleCommand(){

    }
}
