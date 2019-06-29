package command.clientCommand;

import command.clientCommand.ClientCommand;
import server.ClientHandler;

import java.io.DataOutputStream;

import static command.CommandType.*;

public class SaveCmd extends ClientCommand {
    public SaveCmd() {
        type = SAVE;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
    }
}
