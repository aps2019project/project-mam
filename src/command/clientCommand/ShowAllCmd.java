package command.clientCommand;

import command.CommandType;

import java.io.DataOutputStream;

import static command.CommandType.*;

public class ShowAllCmd extends ClientCommand {
    public ShowAllCmd() {
        type = SHOWALL;
    }

    @Override
    public void handleCommand(DataOutputStream output) {
        super.handleCommand(output);
    }
}
