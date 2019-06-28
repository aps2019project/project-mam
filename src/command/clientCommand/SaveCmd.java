package command.clientCommand;

import command.clientCommand.ClientCommand;

import java.io.DataOutputStream;

import static command.CommandType.*;

public class SaveCmd extends ClientCommand {
    public SaveCmd() {
        type = SAVE;
    }

    @Override
    public void handleCommand(DataOutputStream output) {
        super.handleCommand(output);
    }
}
