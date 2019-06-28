package command.clientCommand;

import command.CommandType;
import command.clientCommand.ClientCommand;

import java.io.DataOutputStream;

public class SignUpCmd extends ClientCommand {

    private String userName;
    private String pass;

    public SignUpCmd(String userName, String pass) {
        this.userName = userName;
        this.pass = pass;
        type = CommandType.SIGNUP;
    }

    @Override
    public void handleCommand(DataOutputStream output) {
        super.handleCommand(output);
    }
}
